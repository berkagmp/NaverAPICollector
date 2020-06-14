package com.berkagmp.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.berkagmp.app.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
