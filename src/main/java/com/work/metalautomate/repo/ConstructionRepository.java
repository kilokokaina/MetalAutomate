package com.work.metalautomate.repo;

import com.work.metalautomate.model.Construction;
import com.work.metalautomate.model.Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConstructionRepository extends JpaRepository<Construction, Long> {
    Construction findByConstName(String constName);

    @Query(value = "SELECT * FROM construction WHERE detail_name LIKE %:name%", nativeQuery = true)
    List<Detail> findSeveralByName(@Param("name") String constName);
}
