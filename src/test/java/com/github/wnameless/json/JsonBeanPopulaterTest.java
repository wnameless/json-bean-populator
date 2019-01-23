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

    assertEquals(Float.valueOf(12.34f), tb.getF());
    assertEquals(null, tb.getNoF());

    assertEquals(12.34f, tb.getPf(), 0);
    assertEquals(0f, tb.getNoPf(), 0);

    assertEquals(Double.valueOf(123.456), tb.getD());
    assertEquals(null, tb.getNoD());

    assertEquals(123.456, tb.getPd(), 0);
    assertEquals(0.0, tb.getNoPd(), 0);

    assertEquals(true, tb.getB());
    assertEquals(null, tb.getNoB());

    assertEquals(true, tb.isPb());
    assertEquals(false, tb.isNoPb());

    assertEquals(151851850470L, tb.getProduct());
  }

}
