package com.berkagmp.app.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import com.berkagmp.app.entity.Item;
import com.berkagmp.app.entity.Result;

public interface ItemService {

  public List<Item> list();

  public List<Item> listByActive(Boolean active);

  public List<Item> listByStatusAndCreatedAt(Boolean status, String createdAt);

  public Optional<Item> get(Long id);

  public Item save(Item item);

  public Item update(Long itemId, String pId, String title, String mallName, String link,
      Integer lprice,
      Integer deliveryFee, Integer sum, Boolean active)
      throws NoSuchElementException;

  public Item updateSome(Long itemId, String pId, String title, String mallName, String link,
      Integer lprice,
      Integer deliveryFee, Integer sum, Boolean active) throws NoSuchElementException;

  public void delete(Long itemId);

  public Item verifyItem(Long id);

  public Result collect(String keyword);

  public void collect();
}
