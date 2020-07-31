package com.berkagmp.app.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.berkagmp.app.entity.Product;
import com.berkagmp.app.repo.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

  ProductRepository productRepository;

  @Autowired
  public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public List<Product> list() {
    return productRepository.findAll();
  }

  @Override
  public List<Product> listByActive(Boolean active) {
    return productRepository.findByActive(active);
  }

  @Override
  public Optional<Product> get(Integer id) {
    return productRepository.findById(id);
  }

  @Override
  public Product save(Product product) {
    return productRepository.save(product);
  }

  @Override
  public Product update(Integer productId, String name, String keyword, Float raw, Boolean active)
      throws NoSuchElementException {
    Product product = verifyProduct(productId);
    product.setName(name);
    product.setKeyword(keyword);
    product.setRaw(raw);
    product.setActive(active);

    return productRepository.save(product);
  }

  @Override
  public Product updateSome(Integer productId, String name, String keyword, Float raw,
      Boolean active) throws NoSuchElementException {
    Product product = verifyProduct(productId);

    if (name != null)
      product.setName(name);

    if (keyword != null)
      product.setKeyword(keyword);

    if (raw != null)
      product.setRaw(raw);

    if (active != null)
      product.setActive(active);

    return productRepository.save(product);
  }

  @Override
  public void delete(Integer productId) {
    productRepository.deleteById(productId);
  }

  @Override
  public Product verifyProduct(int id) {
    return productRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("Product does not exist " + id));
  }

}
