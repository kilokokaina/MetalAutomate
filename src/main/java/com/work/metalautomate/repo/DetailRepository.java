package com.work.metalautomate.repo;

import com.work.metalautomate.model.Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailRepository extends JpaRepository<Detail, Long> {
    Detail findByDetailName(String detailName);

    @Query(value = "SELECT * FROM detail WHERE detail_name LIKE %:name%", nativeQuery = true)
    List<Detail> findSeveralByName(@Param("name") String detailName);
}
