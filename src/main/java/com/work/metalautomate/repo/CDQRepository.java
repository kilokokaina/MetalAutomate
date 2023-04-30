package com.work.metalautomate.repo;

import com.work.metalautomate.model.quantity.ConstDetailQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CDQRepository extends JpaRepository<ConstDetailQuantity, Long> {
    ConstDetailQuantity findByDetailId(Long id);
    List<ConstDetailQuantity> findByConstructionId(Long id);
}
