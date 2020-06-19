package com.github.wnameless.json.beanpopulator;

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

  public String getStr() {
    return str;
  }

  public void setStr(String str) {
    this.str = str;
  }

  public String getNoStr() {
    return noStr;
  }

  public void setNoStr(String noStr) {
    this.noStr = noStr;
  }

  public CharSequence getCs() {
    return cs;
  }

  public void setCs(CharSequence cs) {
    this.cs = cs;
  }

  public CharSequence getNoCs() {
    return noCs;
  }

  public void setNoCs(CharSequence noCs) {
    this.noCs = noCs;
  }

  public Integer getI() {
    return i;
  }

  public void setI(Integer i) {
    this.i = i;
  }

  public Integer getNoI() {
    return noI;
  }

  public void setNoI(Integer noI) {
    this.noI = noI;
  }

  public int getPi() {
    return pi;
  }

  public void setPi(int pi) {
    this.pi = pi;
  }

  public int getNoPi() {
    return noPi;
  }

  public void setNoPi(int noPi) {
    this.noPi = noPi;
  }

  public Long getL() {
    return l;
  }

  public void setL(Long l) {
    this.l = l;
  }

  public Long getNoL() {
    return noL;
  }

  public void setNoL(Long noL) {
    this.noL = noL;
  }

  public long getPl() {
    return pl;
  }

  public void setPl(long pl) {
    this.pl = pl;
  }

  public long getNoPl() {
    return noPl;
  }

  public void setNoPl(long noPl) {
    this.noPl = noPl;
  }

  public Float getF() {
    return f;
  }

  public void setF(Float f) {
    this.f = f;
  }

  public Float getNoF() {
    return noF;
  }

  public void setNoF(Float noF) {
    this.noF = noF;
  }

  public float getPf() {
    return pf;
  }

  public void setPf(float pf) {
    this.pf = pf;
  }

  public float getNoPf() {
    return noPf;
  }

  public void setNoPf(float noPf) {
    this.noPf = noPf;
  }

  public Double getD() {
    return d;
  }

  public void setD(Double d) {
    this.d = d;
  }

  public Double getNoD() {
    return noD;
  }

  public void setNoD(Double noD) {
    this.noD = noD;
  }

  public double getPd() {
    return pd;
  }

  public void setPd(double pd) {
    this.pd = pd;
  }

  public double getNoPd() {
    return noPd;
  }

  public void setNoPd(double noPd) {
    this.noPd = noPd;
  }

  public Boolean getB() {
    return b;
  }

  public void setB(Boolean b) {
    this.b = b;
  }

  public Boolean getNoB() {
    return noB;
  }

  public void setNoB(Boolean noB) {
    this.noB = noB;
  }

  public boolean isPb() {
    return pb;
  }

  public void setPb(boolean pb) {
    this.pb = pb;
  }

  public boolean isNoPb() {
    return noPb;
  }

  public void setNoPb(boolean noPb) {
    this.noPb = noPb;
  }

  public long getProduct() {
    return product;
  }

  public void setProduct(long product) {
    this.product = product;
  }

  public double getProduct2() {
    return product2;
  }

  public void setProduct2(double product2) {
    this.product2 = product2;
  }

}
