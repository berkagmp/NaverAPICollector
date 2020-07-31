package com.berkagmp.app.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.berkagmp.app.entity.Brand;
import com.berkagmp.app.repo.BrandRepository;

@Service
public class BrandServiceImpl implements BrandService {
  BrandRepository brandRepository;

  @Autowired
  public BrandServiceImpl(BrandRepository brandRepository) {
    this.brandRepository = brandRepository;
  }

  public List<Brand> list() {
    return brandRepository.findAll();
  }

  public List<Brand> listByActive(Boolean active) {
    return brandRepository.findByActive(active);
  }

  public Optional<Brand> get(Integer id) {
    return brandRepository.findById(id);
  }

  @Transactional
  public Brand save(Brand brand) {
    return brandRepository.save(brand);
  }

  @Transactional
  public Brand update(Integer brandId, String name, Boolean active) throws NoSuchElementException {
    Brand brand = verifyBrand(brandId);
    brand.setName(name);
    brand.setActive(active);

    return brandRepository.save(brand);
  }

  @Transactional
  public Brand updateSome(Integer brandId, String name, Boolean active)
      throws NoSuchElementException {
    Brand brand = verifyBrand(brandId);

    if (name != null)
      brand.setName(name);

    if (active != null)
      brand.setActive(active);

    return brandRepository.save(brand);
  }

  @Transactional
  public void delete(Integer brandId) {
    brandRepository.deleteById(brandId);
  }

  public Brand verifyBrand(int id) {
    return brandRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("Brand does not exist " + id));
  }
}
