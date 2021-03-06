package com.berkagmp.app.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import com.berkagmp.app.entity.Brand;

@SpringBootTest
@Transactional
class BrandRepositoryTest {

  @Autowired
  BrandRepository brandRepository;

  Brand b;

  @BeforeEach
  void setup() {
    b = brandRepository.save(new Brand("brand", true));
  }

  @Test
  void insert_update() {
    final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    Integer i = b.getId();

    assertNotNull(b.getId());
    assertEquals(b.getName(), "brand");
    assertEquals(b.getActive(), true);
    assertNotNull(b.getCreatedAt());
    assertNotNull(b.getUpdatedAt());

    System.out.println(dateFormat.format(new Date()));
    System.out.println(b.toString());

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    b.setName("update");
    b.setActive(false);

    b = brandRepository.saveAndFlush(b);

    assertEquals(i, b.getId());
    assertEquals(b.getName(), "update");
    assertEquals(b.getActive(), false);

    System.out.println(dateFormat.format(new Date()));
    System.out.println(b.toString());
  }

  @Test
  void list() {
    List<Brand> list = brandRepository.findAll();
    assertTrue(list.size() > 0);
  }

  @Test
  void listByActive() {
    List<Brand> list = brandRepository.findByActive(false);
    assertEquals(list.size(), 0);
  }

  @Test
  void get() {
    Brand brand = brandRepository.getOne(b.getId());
    assertNotNull(brand.getCreatedAt());
    assertNotNull(brand.getUpdatedAt());
  }

}
