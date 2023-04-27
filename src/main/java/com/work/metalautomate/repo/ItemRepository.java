package com.work.metalautomate.repo;

import com.work.metalautomate.model.Detail;
import com.work.metalautomate.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findByItemName(String itemName);

    @Query(value = "SELECT * FROM item WHERE detail_name LIKE %:name%", nativeQuery = true)
    List<Detail> findSeveralByName(@Param("name") String itemName);
}
