package com.github.wnameless.json;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonValue;

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
