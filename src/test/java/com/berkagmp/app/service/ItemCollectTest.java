package com.berkagmp.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import com.berkagmp.app.entity.Item;
import com.berkagmp.app.entity.Product;

@SpringBootTest
@Transactional
public class ItemCollectTest {

  @Autowired
  ProductService productService;

  @Autowired
  ItemService itemService;

  @Test
  public void collect() {
    List<Product> products = productService.listByActive(true);

    products.forEach(p -> {
      List<Item> items = itemService.collect(p.getKeyword()).getItems().stream().map(i -> {
        i.setProduct(p);
        return i;
      }).collect(Collectors.toList());

      items.forEach(i -> itemService.save(i));
    });

    List<Item> items = itemService.list();

    items.forEach(i -> System.out.println(i.toString()));

    assertTrue(items.size() > 0);
  }

  @Test
  void listByStatusAndCreatedAt() {
    collect();

    LocalDate today = LocalDate.now();
    String formattedDate = today.format(DateTimeFormatter.ofPattern("yyMMdd"));

    List<Item> items = itemService.listByStatusAndCreatedAt(false, formattedDate);
    assertTrue(items.size() > 0);
  }

}
