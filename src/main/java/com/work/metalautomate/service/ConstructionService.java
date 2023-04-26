package com.work.metalautomate.service;

import com.work.metalautomate.model.Construction;

public interface ConstructionService {
    void save(Construction construction);
    Construction findById(Long id);
    Construction findByName(String constName);
    void deleteById(Long id);
}
