package com.berkagmp.app.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.berkagmp.app.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
  public List<Product> findByActive(Boolean active);
}
