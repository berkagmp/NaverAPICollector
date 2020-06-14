package com.berkagmp.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.berkagmp.app.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer> {

}
