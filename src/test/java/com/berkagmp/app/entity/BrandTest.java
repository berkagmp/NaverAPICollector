package com.berkagmp.app.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BrandTest {
  @Test
  public void testConstructor() {
    Brand b = new Brand("brand", true);

    assertNull(b.getId());
    assertEquals(b.getName(), "brand");
    assertEquals(b.getActive(), true);
    assertNull(b.getCreated_at());
    assertNull(b.getUpdated_at());
  }

  @Test
  public void equalsHashcodeVerify() {
    Brand b1 = new Brand("brand", true);
    Brand b2 = new Brand("brand", true);

    assertTrue(b1.equals(b2));
    assertFalse(b1 == b2);
    assertEquals(b1, b2);
    assertEquals(b1.hashCode(), b2.hashCode());
  }
}
