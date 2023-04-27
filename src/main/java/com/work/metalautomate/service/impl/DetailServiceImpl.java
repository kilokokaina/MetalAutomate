package com.work.metalautomate.service.impl;

import com.work.metalautomate.model.Detail;
import com.work.metalautomate.repo.DetailRepository;
import com.work.metalautomate.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailServiceImpl implements DetailService {
    public final DetailRepository detailRepository;

    @Autowired
    public DetailServiceImpl(DetailRepository detailRepository) {
        this.detailRepository = detailRepository;
    }

    @Override
    public void save(Detail detail) {
        detailRepository.save(detail);
    }

    @Override
    public Detail findById(Long id) {
        return detailRepository.findById(id).isPresent() ?
                detailRepository.findById(id).get() : null;
    }

    @Override
    public Detail findByName(String detailName) {
        return detailRepository.findByDetailName(detailName);
    }

    @Override
    public void deleteById(Long id) {
        detailRepository.deleteById(id);
    }

    public List<Detail> findSeveralByName(String detailName) {
        return detailRepository.findSeveralByName(detailName);
    }
}
