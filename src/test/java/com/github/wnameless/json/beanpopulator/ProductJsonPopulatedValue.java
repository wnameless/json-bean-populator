package com.github.wnameless.json.beanpopulator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ProductJsonPopulatedValue implements JsonPopulatedValueCustomizer {

  @Override
  public Object toValue(String json) {
    ObjectNode js;
    try {
      js = (ObjectNode) new ObjectMapper().readTree(json);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    return js.get("numbers").get("whole").get(0).asLong()
        * js.get("numbers").get("whole").get(1).asLong();
  }

}
