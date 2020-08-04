package com.berkagmp.app.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
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
class ItemRepositoryTest {

  @Autowired
  BrandRepository brandRepository;

  @Autowired
  ProductRepository productRepository;

  @Autowired
  ItemRepository itemRepository;

  Brand b;
  Product p;
  Item i;

  @BeforeEach
  void setup() {
    b = brandRepository.save(new Brand("brand", true));
    p = productRepository.save(new Product("product", true, "keyword", 10.0f, b));
    i = itemRepository
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
    assertNull(i.getCreated_at());

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    i.setTitle("update");
    i.setActive(false);
    i.setDeliveryFee(0);

    i = itemRepository.save(i);

    assertEquals(l, i.getId());
    assertEquals(i.getTitle(), "update");
    assertEquals(i.getActive(), false);
    assertEquals(i.getDeliveryFee(), 0);
  }

  @Test
  void list() {
    List<Item> list = itemRepository.findAll();
    assertTrue(list.size() > 0);
  }

  @Test
  void listByActive() {
    List<Item> list = itemRepository.findByActive(false);
    assertEquals(list.size(), 0);
  }

  @Test
  void product() {
    assertEquals(i.getProduct().getId(), p.getId());
  }

}
