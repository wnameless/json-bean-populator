/*
 *
 * Copyright 2019 Wei-Ming Wu
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 */
package com.github.wnameless.json.beanpopulator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ProductJsonPopulatedValueWithKeys
    implements JsonPopulatedValueWithKeysCustomizer {

  @Override
  public Object toValue(String json, String[] keys) {
    ObjectNode js;
    try {
      js = (ObjectNode) new ObjectMapper().readTree(json);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    return js.get("numbers").get(keys[0]).get(0).asLong()
        * js.get("numbers").get(keys[1]).get(1).asDouble();
  }

}
