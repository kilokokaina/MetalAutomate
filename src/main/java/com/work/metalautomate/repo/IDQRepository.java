package com.work.metalautomate.repo;

import com.work.metalautomate.model.quantity.ItemDetailQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDQRepository extends JpaRepository<ItemDetailQuantity, Long> {
    ItemDetailQuantity findByDetailId(Long id);
    List<ItemDetailQuantity> findByItemId(Long id);
}