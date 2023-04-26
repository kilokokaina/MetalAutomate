package com.work.metalautomate.service.impl;

import com.work.metalautomate.model.Construction;
import com.work.metalautomate.repo.ConstructionRepository;
import com.work.metalautomate.service.ConstructionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConstructionServiceImpl implements ConstructionService {
    public final ConstructionRepository constructionRepository;

    @Autowired
    public ConstructionServiceImpl(ConstructionRepository constructionRepository) {
        this.constructionRepository = constructionRepository;
    }

    @Override
    public void save(Construction construction) {
        constructionRepository.save(construction);
    }

    @Override
    public Construction findById(Long id) {
        return constructionRepository.findById(id).isPresent() ?
                constructionRepository.findById(id).get() : null;
    }

    @Override
    public Construction findByName(String constName) {
        return constructionRepository.findByConstName(constName);
    }

    @Override
    public void deleteById(Long id) {
        constructionRepository.deleteById(id);
    }
}
