package com.berkagmp.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.berkagmp.app.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer> {

  public List<Brand> findByActive(Boolean active);

}
