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
import com.berkagmp.app.entity.Product;

@SpringBootTest
@Transactional
class ProductServiceTest {

  @Autowired
  BrandService brandService;

  @Autowired
  ProductService productService;

  @Test
  void insert_update() {
    Brand b = brandService.save(new Brand("brand", true));
    Product p = productService.save(new Product("product", true, "keyword", 10.0f, b));

    System.out.println(p.toString());
    assertEquals(p.getName(), "product");
    assertEquals(p.getActive(), true);

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    p.setName("update");
    p.setActive(false);

    p = productService.save(p);

    System.out.println(p.toString());
    assertEquals(p.getName(), "update");
    assertEquals(p.getActive(), false);
  }

  @Test
  void list() {
    Brand b = brandService.save(new Brand("brand", true));
    productService.save(new Product("product", true, "keyword", 10.0f, b));

    List<Product> list = productService.list();
    assertTrue(list.size() > 0);
  }

  @Test
  void get() {
    Brand b = brandService.save(new Brand("brand", true));
    int product_id =
        productService.save(new Product("product", false, "keyword", 10.0f, b)).getId();

    Optional<Product> p = productService.get(product_id);
    assertTrue(p.isPresent());
    assertFalse(p.get().getActive());
  }

}
