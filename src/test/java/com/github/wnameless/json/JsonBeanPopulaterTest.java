package com.github.wnameless.json;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class JsonBeanPopulaterTest {

  TestBean tb;
  String json;

  @Before
  public void before() throws IOException {
    tb = new TestBean();
    URL url = Resources.getResource("populated-data.json");
    json = Resources.toString(url, Charsets.UTF_8);
  }

  @Test
  public void testPopulate() {
    tb.setPopulatedData(json);

    assertEquals("Hello", tb.getStr());
    assertEquals("Hello", tb.getCs());
    assertEquals(Integer.valueOf(123), tb.getI());
    assertEquals(123, tb.getPi());
    assertEquals(Long.valueOf(1234567890L), tb.getL());
    assertEquals(1234567890L, tb.getPl());
    assertEquals(Float.valueOf(12.34f), tb.getF());
    assertEquals(12.34f, tb.getPf(), 0);
    assertEquals(Double.valueOf(123.456), tb.getD());
    assertEquals(123.456, tb.getPd(), 0);
    assertEquals(true, tb.getB());
    assertEquals(true, tb.isPb());
    assertEquals(151851850470L, tb.getProduct());
  }

}
