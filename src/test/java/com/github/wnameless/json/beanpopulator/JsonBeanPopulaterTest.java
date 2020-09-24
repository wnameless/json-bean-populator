package com.github.wnameless.json.beanpopulator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map.Entry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class JsonBeanPopulaterTest {

  TestBean tb;
  TestBeanForDefaultKeyName tb2;
  String json;
  String jsonForDefaultKeys;

  @BeforeEach
  public void before() throws IOException {
    tb = new TestBean();
    tb2 = new TestBeanForDefaultKeyName();
    json = Resources.toString(Resources.getResource("populated-data.json"),
        Charsets.UTF_8);
    jsonForDefaultKeys = Resources.toString(
        Resources.getResource("data-with-default-keys.json"), Charsets.UTF_8);
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

  @Test
  public void testPopulate2() {
    tb2.setPopulatedJson(jsonForDefaultKeys);

    assertEquals("abc", tb2.getStr());
    assertEquals(true, tb2.isBoolPri());
    assertEquals(Boolean.FALSE, tb2.getBoolObj());
    assertNull(tb2.getBoolNull());
    assertEquals(1, tb2.getIntPri());
    assertEquals(2, tb2.getIntObj());
    assertNull(tb2.getIntNull());
    assertEquals(3L, tb2.getLongPri());
    assertEquals(4L, tb2.getLongObj());
    assertNull(tb2.getLongNull());
    assertEquals(new BigInteger("5"), tb2.getBigIntObj());
    assertNull(tb2.getBigIntNull());
    assertEquals(6.0f, tb2.getFloatPri());
    assertEquals(7.0f, tb2.getFloatObj());
    assertNull(tb2.getFloatNull());
    assertEquals(8.0, tb2.getDoublePri());
    assertEquals(9.0, tb2.getDoubleObj());
    assertNull(tb2.getDoubleNull());
    assertEquals(new BigDecimal("10.0"), tb2.getBigDecObj());
    assertNull(tb2.getBigDecNull());
  }

  @Test
  public void testBeanToJson()
      throws JsonMappingException, JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    ObjectNode on = (ObjectNode) mapper.readTree(jsonForDefaultKeys);
    on.set("date", mapper.valueToTree("2020-10-10"));
    on.set("dateNil", mapper.valueToTree(null));
    on.set("list", mapper.valueToTree(Arrays.asList("a", "b", "c")));
    String exp = on.toString();
    String act = mapper.readTree(new TestBeanToJson().beanToJson()).toString();
    assertEquals(exp, act);

    Iterator<Entry<String, JsonNode>> iter = on.fields();
    while (iter.hasNext()) {
      Entry<String, JsonNode> f = iter.next();
      if (f.getValue().isNull()) {
        iter.remove();
      }
    }
    String expIgnoreNull = on.toString();
    String actIgnoreNull =
        mapper.readTree(new TestBeanToJson().beanToJson(true)).toString();
    assertEquals(expIgnoreNull, actIgnoreNull);
  }

}
