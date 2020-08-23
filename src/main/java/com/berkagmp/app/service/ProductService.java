package com.berkagmp.app.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import com.berkagmp.app.entity.Product;

public interface ProductService {

  public List<Product> list();

  public List<Product> listByActive(Boolean active);

  public Optional<Product> get(Integer id);

  public Product save(Product product);

  public Product update(Integer productId, String name, String keyword, Float raw, Boolean active)
      throws NoSuchElementException;

  public Product updateSome(Integer productId, String name, String keyword, Float raw,
      Boolean active) throws NoSuchElementException;

  public void delete(Integer productId);

  public Product verifyProduct(int id);

}
