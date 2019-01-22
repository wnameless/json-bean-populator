package com.github.wnameless.json;

import lombok.Data;

@Data
public class TestBean implements JsonPopulatable {

  @JsonPopulatedKey("string")
  String str;

  @JsonPopulatedKey("string")
  CharSequence cs;

  @JsonPopulatedKey("numbers.whole[0]")
  Integer i;

  @JsonPopulatedKey("numbers.whole[0]")
  int pi;

  @JsonPopulatedKey("numbers.whole[1]")
  Long l;

  @JsonPopulatedKey("numbers.whole[1]")
  long pl;

  @JsonPopulatedKey("numbers.fraction[0]")
  Float f;

  @JsonPopulatedKey("numbers.fraction[0]")
  float pf;

  @JsonPopulatedKey("numbers.fraction[1]")
  Double d;

  @JsonPopulatedKey("numbers.fraction[1]")
  double pd;

  @JsonPopulatedKey("boolean")
  Boolean b;

  @JsonPopulatedKey("boolean")
  boolean pb;

  @JsonPopulatedValue(ProductJsonPopulatedValue.class)
  long product;

}
