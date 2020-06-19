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

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.wnameless.json.base.JacksonJsonValue;
import com.github.wnameless.json.base.JsonValueBase;
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
    ObjectMapper mapper = new ObjectMapper();
    JacksonJsonValue jsonVal;
    try {
      jsonVal = new JacksonJsonValue(mapper.readTree(json));
      setPopulatedJson(jsonVal);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Populates the Java bean which implements the {@link JsonPopulatable}
   * interface with given {@link JsonValueBase}.
   * 
   * @param jsonVal
   *          used to populate the Java bean
   */
  default void setPopulatedJson(JsonValueBase<?> jsonVal) {
    String flattenedJson = JsonFlattener.flatten(jsonVal);
    ObjectNode jo;
    try {
      jo = (ObjectNode) new ObjectMapper().readTree(flattenedJson);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    List<String> keys = new ArrayList<>();
    jo.fieldNames().forEachRemaining(key -> keys.add(key));

    Class<?> klass = this.getClass();
    for (Field f : klass.getDeclaredFields()) {
      if (f.isAnnotationPresent(JsonPopulatedKey.class)) {
        JsonPopulatedKey anno = f.getAnnotation(JsonPopulatedKey.class);
        String keyName = anno.value();
        if (keys.contains(keyName)) {
          JsonNode val = jo.get(keyName);
          Class<?> type = f.getType();
          f.setAccessible(true);
          if (val.isTextual() && type.isAssignableFrom(String.class)) {
            try {
              f.set(this, val.asText());
            } catch (IllegalArgumentException | IllegalAccessException e) {}
          } else if (val.isIntegralNumber() && type.equals(Integer.class)) {
            try {
              f.set(this, val.asInt());
            } catch (IllegalArgumentException | IllegalAccessException e) {}
          } else if (val.isIntegralNumber() && type.equals(int.class)) {
            try {
              f.set(this, val.asInt());
            } catch (IllegalArgumentException | IllegalAccessException e) {}
          } else if (val.isIntegralNumber() && type.equals(Long.class)) {
            try {
              f.set(this, val.asLong());
            } catch (IllegalArgumentException | IllegalAccessException e) {}
          } else if (val.isIntegralNumber() && type.equals(long.class)) {
            try {
              f.set(this, val.asLong());
            } catch (IllegalArgumentException | IllegalAccessException e) {}
          } else if (val.isFloatingPointNumber() && type.equals(Float.class)) {
            try {
              f.set(this, (float) val.asDouble());
            } catch (IllegalArgumentException | IllegalAccessException e) {}
          } else if (val.isFloatingPointNumber() && type.equals(float.class)) {
            try {
              f.set(this, (float) val.asDouble());
            } catch (IllegalArgumentException | IllegalAccessException e) {}
          } else if (val.isFloatingPointNumber() && type.equals(Double.class)) {
            try {
              f.set(this, val.asDouble());
            } catch (IllegalArgumentException | IllegalAccessException e) {}
          } else if (val.isFloatingPointNumber() && type.equals(double.class)) {
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
          jpvc = jpv.value().getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException
            | IllegalArgumentException | InvocationTargetException
            | NoSuchMethodException | SecurityException e) {
          throw new RuntimeException(e);
        }
        try {
          f.setAccessible(true);
          f.set(this, jpvc.toValue(jsonVal.toString()));
        } catch (IllegalArgumentException | IllegalAccessException e) {
          throw new RuntimeException(e);
        }
      } else if (f.isAnnotationPresent(JsonPopulatedValueWithKeys.class)) {
        JsonPopulatedValueWithKeys jpvwk =
            f.getAnnotation(JsonPopulatedValueWithKeys.class);
        JsonPopulatedValueWithKeysCustomizer jpvwkc;
        String[] jsonKeys;
        try {
          jpvwkc = jpvwk.customizer().getConstructor().newInstance();
          jsonKeys = jpvwk.keys();
        } catch (InstantiationException | IllegalAccessException
            | IllegalArgumentException | InvocationTargetException
            | NoSuchMethodException | SecurityException e) {
          throw new RuntimeException(e);
        }
        try {
          f.setAccessible(true);
          f.set(this, jpvwkc.toValue(jsonVal.toString(), jsonKeys));
        } catch (IllegalArgumentException | IllegalAccessException e) {
          throw new RuntimeException(e);
        }
      }
    }
  }

}
