package com.work.metalautomate.repo.manufacture;

import com.work.metalautomate.model.manufacture.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findByItemName(String itemName);

    @Query(value = "SELECT * FROM item WHERE item_name LIKE %:name%", nativeQuery = true)
    List<Item> findSeveralByName(@Param("name") String itemName);
}
