package com.github.wnameless.json.beanpopulator;

import com.github.wnameless.json.beanpopulator.JsonPopulatable;
import com.github.wnameless.json.beanpopulator.JsonPopulatedKey;
import com.github.wnameless.json.beanpopulator.JsonPopulatedValue;
import com.github.wnameless.json.beanpopulator.JsonPopulatedValueWithKeys;

import lombok.Data;

@Data
public class TestBean implements JsonPopulatable {

  @JsonPopulatedKey("string")
  String str;

  @JsonPopulatedKey("numbers.whole[0]")
  String noStr;

  @JsonPopulatedKey("string")
  CharSequence cs;

  @JsonPopulatedKey("numbers.whole[0]")
  CharSequence noCs;

  @JsonPopulatedKey("numbers.whole[0]")
  Integer i;

  @JsonPopulatedKey("numbers.fraction[0]")
  Integer noI;

  @JsonPopulatedKey("numbers.whole[0]")
  int pi;

  @JsonPopulatedKey("numbers.fraction[0]")
  int noPi;

  @JsonPopulatedKey("numbers.whole[1]")
  Long l;

  @JsonPopulatedKey("numbers.fraction[1]")
  Long noL;

  @JsonPopulatedKey("numbers.whole[1]")
  long pl;

  @JsonPopulatedKey("numbers.fraction[1]")
  long noPl;

  @JsonPopulatedKey("numbers.fraction[0]")
  Float f;

  @JsonPopulatedKey("string")
  Float noF;

  @JsonPopulatedKey("numbers.fraction[0]")
  float pf;

  @JsonPopulatedKey("string")
  float noPf;

  @JsonPopulatedKey("numbers.fraction[1]")
  Double d;

  @JsonPopulatedKey("string")
  Double noD;

  @JsonPopulatedKey("numbers.fraction[1]")
  double pd;

  @JsonPopulatedKey("string")
  double noPd;

  @JsonPopulatedKey("boolean")
  Boolean b;

  @JsonPopulatedKey("string")
  Boolean noB;

  @JsonPopulatedKey("boolean")
  boolean pb;

  @JsonPopulatedKey("string")
  boolean noPb;

  @JsonPopulatedValue(ProductJsonPopulatedValue.class)
  long product;

  @JsonPopulatedValueWithKeys(
      customizer = ProductJsonPopulatedValueWithKeys.class,
      keys = { "whole", "fraction" })
  double product2;

}
