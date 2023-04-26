package com.work.metalautomate.repo;

import com.work.metalautomate.model.Construction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConstructionRepository extends JpaRepository<Construction, Long> {
    Construction findByConstName(String constName);
}
