[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.wnameless.json/json-bean-populator/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.wnameless.json/json-bean-populator)

json-bean-populator
=============
Populate Java beans by annotations and given JSON data.

## Maven Repo
```xml
<dependency>
	<groupId>com.github.wnameless.json</groupId>
	<artifactId>json-bean-populator</artifactId>
	<version>0.2.0</version>
</dependency>
```
Since v0.2.0, [JsonValueBase](https://github.com/wnameless/json-base) is accepted in JsonPopulatable.

## Purpose & Quick Start
This json-bean-populator is not an Java object serializer or deserializer.<br>
It only focuses on populating Java object fields based on complex and nested JSON data. 
```java
public class TestBean implements JsonPopulatable {

  @JsonPopulatedKey("string")
  String str;

  @JsonPopulatedKey("numbers.whole[0]")
  Integer i;

  @JsonPopulatedKey("numbers.fraction[1]")
  Double d;

  @JsonPopulatedKey("boolean")
  Boolean b;

  @JsonPopulatedValue(ProductJsonPopulatedValue.class)
  long product;

  \\ Getters and Setters
  \\ ...
}
```

Class ProductJsonPopulatedValue
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

populated-data.json:
```json
{
    "string": "Hello",
    "numbers": {
        "whole": [123, 1234567890],
        "fraction": [12.34, 123.456]
    },
    "boolean": true
}
```

Result:
```java
TestBean tb = new TestBean();
String populatedData = "{...}"; // populated-data.json is defined on above lines
tb.setPopulatedJson(populatedData); 

Sytem.out.println(tb.getStr());     // Hello
Sytem.out.println(tb.getI());       // 123
Sytem.out.println(tb.getD());       // 123.456
Sytem.out.println(tb.getB());       // true
Sytem.out.println(tb.getProduct()); // 151851850470
```
