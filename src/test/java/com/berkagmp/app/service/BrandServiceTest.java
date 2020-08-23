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

@SpringBootTest
@Transactional
class BrandServiceTest {

  @Autowired
  BrandService brandService;

  Brand b;

  @BeforeEach
  void setup() {
    b = brandService.save(new Brand("brand", true));
  }

  @Test
  void insert_update() {
    Integer i = b.getId();

    assertEquals(b.getName(), "brand");
    assertEquals(b.getActive(), true);
    assertNotNull(b.getCreatedAt());
    assertNotNull(b.getUpdatedAt());

    System.out.println(b.toString());

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    b.setName("update");
    b.setActive(false);

    b = brandService.save(b);

    assertEquals(i, b.getId());
    assertEquals(b.getName(), "update");
    assertEquals(b.getActive(), false);

    System.out.println(b.toString());
  }

  @Test
  void list() {
    List<Brand> list = brandService.list();
    assertTrue(list.size() > 0);
  }

  @Test
  void listByActive() {
    List<Brand> list = brandService.listByActive(false);
    assertEquals(list.size(), 0);
  }

  @Test
  void get() {
    Optional<Brand> brand = brandService.get(b.getId());
    assertTrue(brand.isPresent());
    assertTrue(brand.get().getActive());
    assertNotNull(brand.get().getCreatedAt());
    assertNotNull(brand.get().getUpdatedAt());
  }

}
