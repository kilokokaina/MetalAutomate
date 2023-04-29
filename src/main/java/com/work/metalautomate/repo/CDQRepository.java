package com.work.metalautomate.repo;

import com.work.metalautomate.model.quantity.ConstDetailQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CDQRepository extends JpaRepository<ConstDetailQuantity, Long> {
}
