package com.berkagmp.app.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.berkagmp.app.entity.Brand;

@SpringBootTest
@Transactional
class BrandRepositoryTest {

	@Autowired
	BrandRepository brandRepository;

	@Test
	void insert_update() {
		Brand b = brandRepository.save(new Brand("brand", true));

		System.out.println(b.toString());
		assertEquals(b.getName(), "brand");
		assertEquals(b.getActive(), true);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		b.setName("update");
		b.setActive(false);

		b = brandRepository.save(b);

		System.out.println(b.toString());
		assertEquals(b.getName(), "update");
		assertEquals(b.getActive(), false);
	}

	@Test
	void list() {
		brandRepository.save(new Brand("brand", true));

		List<Brand> list = brandRepository.findAll();
		assertTrue(list.size() > 0);
	}

}
