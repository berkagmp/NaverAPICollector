package com.berkagmp.app.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ItemTest {

  @Test
  public void testConstructor() {
    Brand b = new Brand("brand", true);
    Product p = new Product("product", true, "keyword", 10.0f, b);
    Item i = new Item("pId", "title", "mallName", "http://", 10000, 2000, 12000, true, p);

    assertNull(i.getId());
    assertEquals(i.getPId(), "pId");
    assertEquals(i.getTitle(), "title");
    assertEquals(i.getMallName(), "mallName");
    assertEquals(i.getLink(), "http://");
    assertEquals(i.getLprice(), 10000);
    assertEquals(i.getDeliveryFee(), 2000);
    assertEquals(i.getSum(), 12000);
    assertTrue(i.getActive());
    assertNull(i.getCreatedAt());
  }

  @Test
  public void equalsHashcodeVerify() {
    Brand b = new Brand("brand", true);
    Product p = new Product("product", true, "keyword", 10.0f, b);
    Item i1 = new Item("pId", "title", "mallNale", "http://", 10000, 2000, 12000, true, p);
    Item i2 = new Item("pId", "title", "mallNale", "http://", 10000, 2000, 12000, true, p);

    assertTrue(i1.equals(i2));
    assertFalse(i1 == i2);
    assertEquals(i1, i2);
    assertEquals(i1.hashCode(), i2.hashCode());
  }

}
