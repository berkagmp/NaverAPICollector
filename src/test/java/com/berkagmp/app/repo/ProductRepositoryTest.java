package com.berkagmp.app.repo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.berkagmp.app.entity.Brand;
import com.berkagmp.app.entity.Product;

@SpringBootTest
@Transactional
class ProductRepositoryTest {

	@Autowired
	BrandRepository brandRepository;

	@Autowired
	ProductRepository productRepository;

	@Test
	void insert_update() {
		Brand b = brandRepository.save(new Brand("brand", true));

		Product p = productRepository.save(new Product("product", true, "keyword", 10.0f, b.getId()));

		System.out.println(p.toString());
		assertEquals(p.getName(), "product");
		assertEquals(p.getActive(), true);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		p.setName("update");
		p.setActive(false);

		p = productRepository.save(p);

		System.out.println(p.toString());
		assertEquals(p.getName(), "update");
		assertEquals(p.getActive(), false);
	}

	@Test
	void list() {
		Brand b = brandRepository.save(new Brand("brand", true));

		productRepository.save(new Product("product", true, "keyword", 10.0f, b.getId()));

		List<Product> list = productRepository.findAll();
		assertTrue(list.size() > 0);
	}

}
