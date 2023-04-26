package com.work.metalautomate.service;

import com.work.metalautomate.model.Detail;

public interface DetailService {
    void save(Detail detail);
    Detail findById(Long id);
    Detail findByName(String detailName);
    void deleteById(Long id);
}
