package com.work.metalautomate.repo.quantity;

import com.work.metalautomate.model.quantity.ConstItemQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CIQRepository extends JpaRepository<ConstItemQuantity, Long> {
    ConstItemQuantity findByItemId(Long id);
    List<ConstItemQuantity> findByConstructionId(Long id);
}