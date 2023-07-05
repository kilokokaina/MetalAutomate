package com.work.metalautomate.repo.manufacture;

import com.work.metalautomate.model.manufacture.Construction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConstructionRepository extends JpaRepository<Construction, Long> {
    Construction findByConstName(String constName);

    @Query(value = "SELECT * FROM construction WHERE const_name LIKE %:name%", nativeQuery = true)
    List<Construction> findSeveralByName(@Param("name") String constName);
}
