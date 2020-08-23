package com.berkagmp.app.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.berkagmp.app.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

  public List<Item> findByActive(Boolean active);

  @Query(
      value = "SELECT * FROM items i WHERE i.status = ?1 and DATE_FORMAT(i.created_at, '%y%m%d') = ?2",
      nativeQuery = true)
  public List<Item> findByStatusAndCreatedAt(Boolean status, String createdAt);

}
