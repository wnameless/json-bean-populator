[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.wnameless.json/json-bean-populator/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.wnameless.json/json-bean-populator)

json-bean-populator
=============
Populate Java beans by annotations and given JSON data.

## Maven Repo
```xml
<dependency>
	<groupId>com.github.wnameless.json</groupId>
	<artifactId>json-bean-populator</artifactId>
	<version>0.6.2</version>
</dependency>
```
Since v0.2.0, [JsonValueBase](https://github.com/wnameless/json-base) is accepted in JsonPopulatable.<br>
Since v0.4.0, Java Module supported and Package name is changed to com.github.wnameless.json.beanpopulator.<br>
Since v0.5.0, BigInteger & BigDecimal are supported.<br>
Since v0.6.0, JsonPopulatedKey uses field name as default key name.<br>
              Turn any JsonPopulatable object to a JSON string by calling #beanToJson method.
Since v0.6.1, JsonPopulatable#beanToJson can ignore null values

## Purpose & Quick Start
This json-bean-populator is not an Java object serializer or deserializer.<br>
It only focuses on populating Java object fields based on complex and nested JSON data. 
```java
public class TestBean implements JsonPopulatable {

  @JsonPopulatedKey
  String str;

  @JsonPopulatedKey("numbers.whole[0]")
  Integer i;

  @JsonPopulatedKey("numbers.fraction[1]")
  Double d;

  @JsonPopulatedKey("boolean")
  Boolean b;

  @JsonifyStrategy(value = ProductStrategy.class, keyName = "p")
  // If no strategy is provided, a default strategy will be applied
  @JsonPopulatedValue(ProductJsonPopulatedValue.class)
  long product;

  @JsonPopulatedValueWithKeys(
      customizer = ProductJsonPopulatedValueWithKeys.class,
      keys = { "whole", "fraction" })
  double product2;

  // Getters and Setters
  // ...
}
```

### Class ProductJsonPopulatedValue
```java
public class ProductJsonPopulatedValue implements JsonPopulatedValueCustomizer {

  @Override
  public Object toValue(String json) {
    JsonValue js = Json.parse(json);
    return js.asObject().get("numbers").asObject().get("whole").asArray().get(0)
        .asLong()
        * js.asObject().get("numbers").asObject().get("whole").asArray().get(1)
            .asLong();
  }

}
```

### Class ProductJsonPopulatedValueWithKeys
```java
public class ProductJsonPopulatedValueWithKeys
    implements JsonPopulatedValueWithKeysCustomizer {

  @Override
  public Object toValue(String json, String[] keys) {
    JsonValue js = Json.parse(json);
    return js.asObject().get("numbers").asObject().get(keys[0]).asArray().get(0)
        .asLong()
        * js.asObject().get("numbers").asObject().get(keys[1]).asArray().get(1)
            .asDouble();
  }

}
```

### Class ProductStrategy
```java
public class ProductStrategy implements JsonifyStrategyProvider {

  @Override
  public String toJson(Field field, Object bean) {
    field.setAccessible(true);
    Long product = null;
    try {
      product = (Long) field.get(bean);
    } catch (IllegalArgumentException | IllegalAccessException e) {
      throw new RuntimeException(e);
    }
    if (product == null) return "null";

    return product.toString();
  }

}

```

### populated-data.json:
```json
{
    "str": "Hello",
    "numbers": {
        "whole": [123, 1234567890],
        "fraction": [12.34, 123.456]
    },
    "boolean": true
}
```

### Result:
```java
TestBean tb = new TestBean();
String populatedData = "{...}"; // populated-data.json is defined on above lines
tb.setPopulatedJson(populatedData); 

Sytem.out.println(tb.getStr());      // Hello
Sytem.out.println(tb.getI());        // 123
Sytem.out.println(tb.getD());        // 123.456
Sytem.out.println(tb.getB());        // true
Sytem.out.println(tb.getProduct());  // 151851850470
Sytem.out.println(tb.getProduct2()); // 15185.088

Sytem.out.println(tb.beanToJson);
// { "str": "Hello", "i": 123, "d": 123.456, "b": true, "p": 151851850470 }
```
