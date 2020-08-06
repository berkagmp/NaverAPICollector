package com.berkagmp.app.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class ProductTest {

  @Test
  public void testConstructor() {
    Brand b = new Brand("brand", true);
    Product p = new Product("product", true, "keyword", 10.0f, b);

    assertNull(p.getId());
    assertEquals(p.getName(), "product");
    assertEquals(p.getActive(), true);
    assertEquals(p.getKeyword(), "keyword");
    assertEquals(p.getRaw(), 10.0f);
    assertNull(p.getCreatedAt());
    assertNull(p.getUpdatedAt());
  }

  @Test
  public void equalsHashcodeVerify() {
    Brand b = new Brand("brand", true);

    Product p1 = new Product("product", true, "keyword", 10.0f, b);
    Product p2 = new Product("product", true, "keyword", 10.0f, b);

    assertTrue(p1.equals(p2));
    assertFalse(p1 == p2);
    assertEquals(p1, p2);
    assertEquals(p1.hashCode(), p2.hashCode());
  }
}
