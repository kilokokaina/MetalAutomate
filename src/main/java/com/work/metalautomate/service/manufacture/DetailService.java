package com.work.metalautomate.service.manufacture;

import com.work.metalautomate.model.manufacture.Detail;

public interface DetailService {
    void save(Detail detail);
    Detail findById(Long id);
    Detail findByName(String detailName);
    void deleteById(Long id);
}
