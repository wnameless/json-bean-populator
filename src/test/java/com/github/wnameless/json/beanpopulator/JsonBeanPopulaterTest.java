package com.github.wnameless.json.beanpopulator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class JsonBeanPopulaterTest {

  TestBean tb;
  String json;

  @BeforeEach
  public void before() throws IOException {
    tb = new TestBean();
    json = Resources.toString(Resources.getResource("populated-data.json"),
        Charsets.UTF_8);
  }

  @Test
  public void testPopulate() {
    tb.setPopulatedJson(json);

    assertEquals("Hello", tb.getStr());
    assertEquals(null, tb.getNoStr());

    assertEquals("Hello", tb.getCs());
    assertEquals(null, tb.getNoCs());

    assertEquals(Integer.valueOf(123), tb.getI());
    assertEquals(null, tb.getNoI());

    assertEquals(123, tb.getPi());
    assertEquals(0, tb.getNoPi());

    assertEquals(Long.valueOf(1234567890L), tb.getL());
    assertEquals(null, tb.getNoL());

    assertEquals(1234567890L, tb.getPl());
    assertEquals(0L, tb.getNoPl());

    assertEquals(BigInteger.valueOf(1234567890L), tb.getBi());
    assertEquals(null, tb.getNoBi());

    assertEquals(Float.valueOf(12.34f), tb.getF());
    assertEquals(null, tb.getNoF());

    assertEquals(Float.valueOf(1234567890), tb.getI2F());

    assertEquals(1234567890f, tb.getI2f());

    assertEquals(Double.valueOf(1234567890), tb.getI2D());

    assertEquals(1234567890d, tb.getI2d());

    assertEquals(BigDecimal.valueOf(1234567890), tb.getI2BD());

    assertEquals(12.34f, tb.getPf(), 0);
    assertEquals(0f, tb.getNoPf(), 0);

    assertEquals(Double.valueOf(123.456), tb.getD());
    assertEquals(null, tb.getNoD());

    assertEquals(123.456, tb.getPd(), 0);
    assertEquals(0.0, tb.getNoPd(), 0);

    assertEquals(BigDecimal.valueOf(123.456), tb.getBd());

    assertEquals(true, tb.getB());
    assertEquals(null, tb.getNoB());

    assertEquals(true, tb.isPb());
    assertEquals(false, tb.isNoPb());

    assertEquals(151851850470L, tb.getProduct());
    assertEquals(15185.088, tb.getProduct2(), 0);
  }

}
