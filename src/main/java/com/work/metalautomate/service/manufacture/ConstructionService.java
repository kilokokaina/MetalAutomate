package com.work.metalautomate.service.manufacture;

import com.work.metalautomate.model.manufacture.Construction;

public interface ConstructionService {
    void save(Construction construction);
    Construction findById(Long id);
    Construction findByName(String constName);
    void deleteById(Long id);
}
