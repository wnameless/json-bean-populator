/*
 *
 * Copyright 2020 Wei-Ming Wu
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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestBeanToJson implements JsonPopulatable {

  @JsonPopulatedKey
  String str = "abc";
  @JsonPopulatedKey
  boolean boolPri = true;
  @JsonPopulatedKey
  Boolean boolObj = false;
  @JsonPopulatedKey
  Boolean boolNull = null;
  @JsonPopulatedKey
  int intPri = 1;
  @JsonPopulatedKey
  Integer intObj = 2;
  @JsonPopulatedKey
  Integer intNull = null;
  @JsonPopulatedKey
  long longPri = 3L;
  @JsonPopulatedKey
  Long longObj = 4L;
  @JsonPopulatedKey
  Long longNull = null;
  @JsonPopulatedKey
  BigInteger bigIntObj = new BigInteger("5");
  @JsonPopulatedKey
  BigInteger bigIntNull = null;
  @JsonPopulatedKey
  float floatPri = 6.0f;
  @JsonPopulatedKey
  Float floatObj = 7.0f;
  @JsonPopulatedKey
  Float floatNull = null;
  @JsonPopulatedKey
  double doublePri = 8.0;
  @JsonPopulatedKey
  Double doubleObj = 9.0;
  @JsonPopulatedKey
  Double doubleNull = null;
  @JsonPopulatedKey
  BigDecimal bigDecObj = new BigDecimal("10.0");
  @JsonPopulatedKey
  BigDecimal bigDecNull = null;

  @JsonifyStrategy(LocalDateStrategy.class)
  LocalDate date = LocalDate.of(2020, 10, 10);
  @JsonifyStrategy(value = LocalDateStrategy.class, keyName = "dateNil")
  LocalDate dateNull = null;

  @JsonifyStrategy
  List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));

}
