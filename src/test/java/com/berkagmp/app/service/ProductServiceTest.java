package com.berkagmp.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import com.berkagmp.app.entity.Brand;
import com.berkagmp.app.entity.Product;

@SpringBootTest
@Transactional
class ProductServiceTest {

  @Autowired
  BrandService brandService;

  @Autowired
  ProductService productService;

  Brand b;
  Product p;

  @BeforeEach
  void setup() {
    b = brandService.save(new Brand("brand", true));
    p = productService.save(new Product("product", true, "keyword", 10.0f, b));
  }

  @Test
  void insert_update() {
    Integer i = p.getId();

    assertEquals(p.getName(), "product");
    assertEquals(p.getActive(), true);
    assertNotNull(p.getCreatedAt());
    assertNotNull(p.getUpdatedAt());

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    p.setName("update");
    p.setActive(false);

    p = productService.save(p);

    assertEquals(i, p.getId());
    assertEquals(p.getName(), "update");
    assertEquals(p.getActive(), false);
  }

  @Test
  void list() {
    List<Product> list = productService.list();
    assertTrue(list.size() > 0);
  }

  @Test
  void listByActive() {
    List<Product> list = productService.listByActive(false);
    assertEquals(list.size(), 0);
  }

  @Test
  void get() {
    Optional<Product> product = productService.get(p.getId());
    assertTrue(product.isPresent());
    assertTrue(product.get().getActive());
    assertNotNull(product.get().getCreatedAt());
    assertNotNull(product.get().getUpdatedAt());
  }

}
