package com.work.metalautomate.repo;

import com.work.metalautomate.model.quantity.ConstItemQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CIQRepository extends JpaRepository<ConstItemQuantity, Long> {
}