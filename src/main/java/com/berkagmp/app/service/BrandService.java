package com.berkagmp.app.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import com.berkagmp.app.entity.Brand;

public interface BrandService {
  public List<Brand> list();

  public List<Brand> listByActive(Boolean active);

  public Optional<Brand> get(Integer id);

  public Brand save(Brand brand);

  public Brand update(Integer brandId, String name, Boolean active) throws NoSuchElementException;

  public Brand updateSome(Integer brandId, String name, Boolean active)
      throws NoSuchElementException;

  public void delete(Integer brandId);

  public Brand verifyBrand(int id);
}
