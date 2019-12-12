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
package com.github.wnameless.json;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonValue;

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
