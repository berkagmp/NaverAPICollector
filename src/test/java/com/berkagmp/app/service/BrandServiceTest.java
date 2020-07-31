package com.berkagmp.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import java.util.Optional;
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

  @Test
  void insert_update() {
    Brand b = brandService.save(new Brand("brand", true));

    System.out.println(b.toString());
    assertEquals(b.getName(), "brand");
    assertEquals(b.getActive(), true);

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    b.setName("update");
    b.setActive(false);

    b = brandService.save(b);

    System.out.println(b.toString());
    assertEquals(b.getName(), "update");
    assertEquals(b.getActive(), false);
  }

  @Test
  void list() {
    brandService.save(new Brand("brand", true));

    List<Brand> list = brandService.list();
    assertTrue(list.size() > 0);
  }

  @Test
  void get() {
    int brand_id = brandService.save(new Brand("brand", false)).getId();

    Optional<Brand> b = brandService.get(brand_id);
    assertTrue(b.isPresent());
    assertFalse(b.get().getActive());
  }

}
