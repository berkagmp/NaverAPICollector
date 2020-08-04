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
import com.berkagmp.app.entity.Item;
import com.berkagmp.app.entity.Product;

@SpringBootTest
@Transactional
class ItemServiceTest {

  @Autowired
  BrandService brandService;

  @Autowired
  ProductService productService;

  @Autowired
  ItemService itemService;

  Brand b;
  Product p;
  Item i;

  @BeforeEach
  void setup() {
    b = brandService.save(new Brand("brand", true));
    p = productService.save(new Product("product", true, "keyword", 10.0f, b));
    i = itemService
        .save(new Item("pId", "title", "mallName", "http://", 10000, 2000, 12000, true, p));
  }

  @Test
  void insert_update() {
    Long l = i.getId();

    assertNotNull(i.getId());
    assertEquals(i.getPId(), "pId");
    assertEquals(i.getTitle(), "title");
    assertEquals(i.getMallName(), "mallName");
    assertEquals(i.getLink(), "http://");
    assertEquals(i.getLprice(), 10000);
    assertEquals(i.getDeliveryFee(), 2000);
    assertEquals(i.getSum(), 12000);
    assertTrue(i.getActive());
    assertNotNull(i.getCreated_at());

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    i.setTitle("update");
    i.setActive(false);
    i.setDeliveryFee(0);

    i = itemService.save(i);

    assertEquals(l, i.getId());
    assertEquals(i.getTitle(), "update");
    assertEquals(i.getActive(), false);
    assertEquals(i.getDeliveryFee(), 0);
  }

  @Test
  void list() {
    List<Item> list = itemService.list();
    assertTrue(list.size() > 0);
  }

  @Test
  void listByActive() {
    List<Item> list = itemService.listByActive(false);
    assertEquals(list.size(), 0);
  }

  @Test
  void get() {
    Optional<Item> item = itemService.get(i.getId());
    assertTrue(item.isPresent());
    assertTrue(item.get().getActive());
    assertNotNull(item.get().getCreated_at());
  }

  @Test
  void product() {
    assertEquals(i.getProduct().getId(), p.getId());
  }

}
