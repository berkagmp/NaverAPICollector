package com.berkagmp.app.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.berkagmp.app.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
  public List<Item> findByActive(Boolean active);
}
