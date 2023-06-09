package com.work.metalautomate.service.impl.manufacture;

import com.work.metalautomate.model.manufacture.Construction;
import com.work.metalautomate.repo.manufacture.ConstructionRepository;
import com.work.metalautomate.service.manufacture.ConstructionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Construction> findSeveralByName(String detailName) {
        return constructionRepository.findSeveralByName(detailName);
    }
}
