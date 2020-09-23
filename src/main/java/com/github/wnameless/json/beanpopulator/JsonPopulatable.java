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
import java.math.BigDecimal;
import java.math.BigInteger;
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
        if (keyName.isEmpty()) keyName = f.getName();
        if (keys.contains(keyName)) {
          JsonNode val = jo.get(keyName);
          Class<?> type = f.getType();
          f.setAccessible(true);
          // Text
          if (val.isTextual() && type.isAssignableFrom(String.class)) {
            try {
              f.set(this, val.asText());
            } catch (IllegalArgumentException | IllegalAccessException e) {}
            // Number
          } else if (val.isNumber()) {
            // Integer
            if (val.isIntegralNumber()) {
              if (type.equals(Integer.class)) {
                try {
                  f.set(this, val.asInt());
                } catch (IllegalArgumentException | IllegalAccessException e) {}
              } else if (type.equals(int.class)) {
                try {
                  f.set(this, val.asInt());
                } catch (IllegalArgumentException | IllegalAccessException e) {}
              } else if (type.equals(Long.class)) {
                try {
                  f.set(this, val.asLong());
                } catch (IllegalArgumentException | IllegalAccessException e) {}
              } else if (type.equals(long.class)) {
                try {
                  f.set(this, val.asLong());
                } catch (IllegalArgumentException | IllegalAccessException e) {}
              } else if (type.equals(BigInteger.class)) {
                try {
                  f.set(this, new BigInteger(val.asText()));
                } catch (IllegalArgumentException | IllegalAccessException e) {}
              }
              // Integer to Floating
              else if (type.equals(Float.class)) {
                try {
                  f.set(this, (float) val.asInt());
                } catch (IllegalArgumentException | IllegalAccessException e) {}
              } else if (type.equals(float.class)) {
                try {
                  f.set(this, (float) val.asInt());
                } catch (IllegalArgumentException | IllegalAccessException e) {}
              } else if (type.equals(Double.class)) {
                try {
                  f.set(this, (double) val.asLong());
                } catch (IllegalArgumentException | IllegalAccessException e) {}
              } else if (type.equals(double.class)) {
                try {
                  f.set(this, (double) val.asLong());
                } catch (IllegalArgumentException | IllegalAccessException e) {}
              } else if (type.equals(BigDecimal.class)) {
                try {
                  f.set(this, new BigDecimal(val.asText()));
                } catch (IllegalArgumentException | IllegalAccessException e) {}
              }
              // Floating
            } else if (val.isFloatingPointNumber()) {
              if (type.equals(Float.class)) {
                try {
                  f.set(this, (float) val.asDouble());
                } catch (IllegalArgumentException | IllegalAccessException e) {}
              } else if (type.equals(float.class)) {
                try {
                  f.set(this, (float) val.asDouble());
                } catch (IllegalArgumentException | IllegalAccessException e) {}
              } else if (type.equals(Double.class)) {
                try {
                  f.set(this, val.asDouble());
                } catch (IllegalArgumentException | IllegalAccessException e) {}
              } else if (type.equals(double.class)) {
                try {
                  f.set(this, val.asDouble());
                } catch (IllegalArgumentException | IllegalAccessException e) {}
              } else if (type.equals(BigDecimal.class)) {
                try {
                  f.set(this, new BigDecimal(val.asText()));
                } catch (IllegalArgumentException | IllegalAccessException e) {}
              }
            }
            // Boolean
          } else if (val.isBoolean()) {
            if (type.equals(Boolean.class)) {
              try {
                f.set(this, val.asBoolean());
              } catch (IllegalArgumentException | IllegalAccessException e) {}
            } else if (type.equals(boolean.class)) {
              try {
                f.set(this, val.asBoolean());
              } catch (IllegalArgumentException | IllegalAccessException e) {}
            }
          }
        }
        // Custom
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

  /**
   * Converts this Java bean to a JSON string. Only fields annotated with
   * {@link JsonPopulatedKey} or {@link JsonifyStrategy } will be converted.
   * 
   * @return a JSON string
   */
  default String beanToJson() {
    return beanToJson(false);
  }

  /**
   * Converts this Java bean to a JSON string. Only fields annotated with
   * {@link JsonPopulatedKey} or {@link JsonifyStrategy } will be converted.
   * 
   * @param ignoreNull
   *          if true null values will be ignored
   * @return a JSON string
   */
  default String beanToJson(boolean ignoreNull) {
    ObjectMapper mapper = new ObjectMapper();
    ObjectNode obj = mapper.createObjectNode();

    for (Field f : getClass().getDeclaredFields()) {
      if (f.isAnnotationPresent(JsonPopulatedKey.class)) {
        JsonPopulatedKey anno = f.getAnnotation(JsonPopulatedKey.class);
        String keyName = anno.value();
        if (keyName.isEmpty()) keyName = f.getName();

        try {
          f.setAccessible(true);
          JsonNode jn = mapper.valueToTree(f.get(this));
          if (!ignoreNull || !jn.isNull()) {
            obj.set(keyName, jn);
          }
        } catch (IllegalArgumentException | IllegalAccessException e) {
          throw new RuntimeException(e);
        }
      }
      // Custom
      else if (f.isAnnotationPresent(JsonifyStrategy.class)) {
        JsonifyStrategy js = f.getAnnotation(JsonifyStrategy.class);
        String keyName = js.keyName();
        if (keyName.isEmpty()) keyName = f.getName();

        JsonifyStrategyProvider jsp;
        try {
          jsp = js.value().getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException
            | IllegalArgumentException | InvocationTargetException
            | NoSuchMethodException | SecurityException e) {
          throw new RuntimeException(e);
        }
        try {
          f.setAccessible(true);
          JsonNode jn = mapper.readTree(jsp.toJson(f, this));
          if (!ignoreNull || !jn.isNull()) {
            obj.set(keyName, jn);
          }
        } catch (IllegalArgumentException | JsonProcessingException e) {
          throw new RuntimeException(e);
        }
      }
    }

    return obj.toString();
  }

}
