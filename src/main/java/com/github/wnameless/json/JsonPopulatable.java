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

import java.lang.reflect.Field;
import java.util.List;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import com.github.wnameless.json.flattener.JsonFlattener;

/**
 * 
 * {@link JsonPopulatable} defines a interface with a default
 * {@link #setPopulatedJson} method which allows
 * 
 */
public interface JsonPopulatable {

  /**
   * Populates the Java bean which implements the {@link JsonPopulatable}
   * interface with given JSON string.
   * 
   * @param json
   *          used to populate the Java bean
   */
  default void setPopulatedJson(String json) {
    String flattenedJson = JsonFlattener.flatten(json);
    JsonValue jv = Json.parse(flattenedJson);
    JsonObject jo = jv.asObject();
    List<String> keys = jo.names();

    Class<?> klass = this.getClass();
    for (Field f : klass.getDeclaredFields()) {
      if (f.isAnnotationPresent(JsonPopulatedKey.class)) {
        JsonPopulatedKey anno = f.getAnnotation(JsonPopulatedKey.class);
        String keyName = anno.value();
        if (keys.contains(keyName)) {
          JsonValue val = jo.get(keyName);
          Class<?> type = f.getType();
          f.setAccessible(true);
          if (val.isString() && type.isAssignableFrom(String.class)) {
            try {
              f.set(this, val.asString());
            } catch (IllegalArgumentException | IllegalAccessException e) {}
          } else if (val.isNumber() && type.equals(Integer.class)) {
            try {
              f.set(this, val.asInt());
            } catch (IllegalArgumentException | IllegalAccessException e) {}
          } else if (val.isNumber() && type.equals(int.class)) {
            try {
              f.set(this, val.asInt());
            } catch (IllegalArgumentException | IllegalAccessException e) {}
          } else if (val.isNumber() && type.equals(Long.class)) {
            try {
              f.set(this, val.asLong());
            } catch (IllegalArgumentException | IllegalAccessException e) {}
          } else if (val.isNumber() && type.equals(long.class)) {
            try {
              f.set(this, val.asLong());
            } catch (IllegalArgumentException | IllegalAccessException e) {}
          } else if (val.isNumber() && type.equals(Float.class)) {
            try {
              f.set(this, val.asFloat());
            } catch (IllegalArgumentException | IllegalAccessException e) {}
          } else if (val.isNumber() && type.equals(float.class)) {
            try {
              f.set(this, val.asFloat());
            } catch (IllegalArgumentException | IllegalAccessException e) {}
          } else if (val.isNumber() && type.equals(Double.class)) {
            try {
              f.set(this, val.asDouble());
            } catch (IllegalArgumentException | IllegalAccessException e) {}
          } else if (val.isNumber() && type.equals(double.class)) {
            try {
              f.set(this, val.asDouble());
            } catch (IllegalArgumentException | IllegalAccessException e) {}
          } else if (val.isBoolean() && type.equals(Boolean.class)) {
            try {
              f.set(this, val.asBoolean());
            } catch (IllegalArgumentException | IllegalAccessException e) {}
          } else if (val.isBoolean() && type.equals(boolean.class)) {
            try {
              f.set(this, val.asBoolean());
            } catch (IllegalArgumentException | IllegalAccessException e) {}
          }
        }
      } else if (f.isAnnotationPresent(JsonPopulatedValue.class)) {
        JsonPopulatedValue jpv = f.getAnnotation(JsonPopulatedValue.class);
        JsonPopulatedValueCustomizer jpvc;
        try {
          jpvc = jpv.value().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
          throw new RuntimeException(e);
        }
        try {
          f.setAccessible(true);
          f.set(this, jpvc.toValue(json));
        } catch (IllegalArgumentException | IllegalAccessException e) {
          throw new RuntimeException(e);
        }
      }
    }
  }

}
