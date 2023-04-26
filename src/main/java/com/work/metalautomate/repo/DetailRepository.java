package com.work.metalautomate.repo;

import com.work.metalautomate.model.Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailRepository extends JpaRepository<Detail, Long> {
    Detail findByDetailName(String detailName);
}
