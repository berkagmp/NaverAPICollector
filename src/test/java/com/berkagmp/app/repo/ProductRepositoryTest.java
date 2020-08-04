package com.berkagmp.app.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import com.berkagmp.app.entity.Brand;
import com.berkagmp.app.entity.Product;

@SpringBootTest
@Transactional
class ProductRepositoryTest {

  @Autowired
  BrandRepository brandRepository;

  @Autowired
  ProductRepository productRepository;

  Brand b;
  Product p;

  @BeforeEach
  void setup() {
    b = brandRepository.save(new Brand("brand", true));
    p = productRepository.save(new Product("product", true, "keyword", 10.0f, b));
  }

  @Test
  void insert_update() {
    Integer i = p.getId();

    assertNotNull(p.getId());
    assertEquals(p.getName(), "product");
    assertEquals(p.getActive(), true);
    assertNotNull(p.getCreated_at());
    assertNotNull(p.getUpdated_at());

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    p.setName("update");
    p.setActive(false);

    p = productRepository.save(p);

    assertEquals(i, p.getId());
    assertEquals(p.getName(), "update");
    assertEquals(p.getActive(), false);
  }

  @Test
  void list() {
    List<Product> list = productRepository.findAll();
    assertTrue(list.size() > 0);
  }

  @Test
  void listByActive() {
    List<Product> list = productRepository.findByActive(false);
    assertEquals(list.size(), 0);
  }

  @Test
  void get() {
    Product product = productRepository.getOne(p.getId());
    assertNotNull(product.getCreated_at());
    assertNotNull(product.getUpdated_at());
  }

}
