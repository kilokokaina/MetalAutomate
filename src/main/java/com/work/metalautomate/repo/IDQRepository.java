package com.work.metalautomate.repo;

import com.work.metalautomate.model.quantity.ItemDetailQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDQRepository extends JpaRepository<ItemDetailQuantity, Long> {
}