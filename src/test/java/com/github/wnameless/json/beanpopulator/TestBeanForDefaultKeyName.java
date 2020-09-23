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

public class TestBeanForDefaultKeyName implements JsonPopulatable {

  @JsonPopulatedKey
  String str;
  @JsonPopulatedKey
  boolean boolPri;
  @JsonPopulatedKey
  Boolean boolObj;
  @JsonPopulatedKey
  Boolean boolNull;
  @JsonPopulatedKey
  int intPri;
  @JsonPopulatedKey
  Integer intObj;
  @JsonPopulatedKey
  Integer intNull;
  @JsonPopulatedKey
  long longPri;
  @JsonPopulatedKey
  Long longObj;
  @JsonPopulatedKey
  Long longNull;
  @JsonPopulatedKey
  BigInteger bigIntObj;
  @JsonPopulatedKey
  BigInteger bigIntNull;
  @JsonPopulatedKey
  float floatPri;
  @JsonPopulatedKey
  Float floatObj;
  @JsonPopulatedKey
  Float floatNull;
  @JsonPopulatedKey
  double doublePri;
  @JsonPopulatedKey
  Double doubleObj;
  @JsonPopulatedKey
  Double doubleNull;
  @JsonPopulatedKey
  BigDecimal bigDecObj;
  @JsonPopulatedKey
  BigDecimal bigDecNull;

  public String getStr() {
    return str;
  }

  public void setStr(String str) {
    this.str = str;
  }

  public boolean isBoolPri() {
    return boolPri;
  }

  public void setBoolPri(boolean boolPri) {
    this.boolPri = boolPri;
  }

  public Boolean getBoolObj() {
    return boolObj;
  }

  public void setBoolObj(Boolean boolObj) {
    this.boolObj = boolObj;
  }

  public Boolean getBoolNull() {
    return boolNull;
  }

  public void setBoolNull(Boolean boolNull) {
    this.boolNull = boolNull;
  }

  public int getIntPri() {
    return intPri;
  }

  public void setIntPri(int intPri) {
    this.intPri = intPri;
  }

  public Integer getIntObj() {
    return intObj;
  }

  public void setIntObj(Integer intObj) {
    this.intObj = intObj;
  }

  public Integer getIntNull() {
    return intNull;
  }

  public void setIntNull(Integer intNull) {
    this.intNull = intNull;
  }

  public long getLongPri() {
    return longPri;
  }

  public void setLongPri(long longPri) {
    this.longPri = longPri;
  }

  public Long getLongObj() {
    return longObj;
  }

  public void setLongObj(Long longObj) {
    this.longObj = longObj;
  }

  public Long getLongNull() {
    return longNull;
  }

  public void setLongNull(Long longNull) {
    this.longNull = longNull;
  }

  public BigInteger getBigIntObj() {
    return bigIntObj;
  }

  public void setBigIntObj(BigInteger bigIntObj) {
    this.bigIntObj = bigIntObj;
  }

  public BigInteger getBigIntNull() {
    return bigIntNull;
  }

  public void setBigIntNull(BigInteger bigIntNull) {
    this.bigIntNull = bigIntNull;
  }

  public float getFloatPri() {
    return floatPri;
  }

  public void setFloatPri(float floatPri) {
    this.floatPri = floatPri;
  }

  public Float getFloatObj() {
    return floatObj;
  }

  public void setFloatObj(Float floatObj) {
    this.floatObj = floatObj;
  }

  public Float getFloatNull() {
    return floatNull;
  }

  public void setFloatNull(Float floatNull) {
    this.floatNull = floatNull;
  }

  public double getDoublePri() {
    return doublePri;
  }

  public void setDoublePri(double doublePri) {
    this.doublePri = doublePri;
  }

  public Double getDoubleObj() {
    return doubleObj;
  }

  public void setDoubleObj(Double doubleObj) {
    this.doubleObj = doubleObj;
  }

  public Double getDoubleNull() {
    return doubleNull;
  }

  public void setDoubleNull(Double doubleNull) {
    this.doubleNull = doubleNull;
  }

  public BigDecimal getBigDecObj() {
    return bigDecObj;
  }

  public void setBigDecObj(BigDecimal bigDecObj) {
    this.bigDecObj = bigDecObj;
  }

  public BigDecimal getBigDecNull() {
    return bigDecNull;
  }

  public void setBigDecNull(BigDecimal bigDecNull) {
    this.bigDecNull = bigDecNull;
  }

}
