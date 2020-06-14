package com.berkagmp.app.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ProductTest {

	@Test
	public void testConstructor() {
		Product p = new Product("product", true, "keyword", 10.0f, 1);

		assertNull(p.getId());
		assertEquals(p.getName(), "product");
		assertEquals(p.getActive(), true);
		assertEquals(p.getKeyword(), "keyword");
		assertEquals(p.getRaw(), 10.0f);
		assertNull(p.getCreated_at());
		assertNull(p.getUpdated_at());
	}

	@Test
	public void equalsHashcodeVerify() {
		Product p1 = new Product("product", true, "keyword", 10.0f, 1);
		Product p2 = new Product("product", true, "keyword", 10.0f, 1);

		assertTrue(p1.equals(p2));
		assertFalse(p1 == p2);
		assertEquals(p1, p2);
		assertEquals(p1.hashCode(), p2.hashCode());
	}
}
