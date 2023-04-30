package com.work.metalautomate.service.impl;

import com.work.metalautomate.model.Construction;
import com.work.metalautomate.model.Detail;
import com.work.metalautomate.model.quantity.ConstDetailQuantity;
import com.work.metalautomate.repo.CDQRepository;
import com.work.metalautomate.service.CDQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CDQServiceImpl implements CDQService {
    private final CDQRepository cdqRepository;

    @Autowired
    public CDQServiceImpl(CDQRepository cdqRepository) {
        this.cdqRepository = cdqRepository;
    }

    @Override
    public void save(ConstDetailQuantity constDetailQuantity) {
        cdqRepository.save(constDetailQuantity);
    }

    @Override
    public ConstDetailQuantity findByID(Long id) {
        return cdqRepository.findById(id).isPresent() ? cdqRepository.findById(id).get() : null;
    }

    @Override
    public ConstDetailQuantity findByDetailID(Long id) {
        return cdqRepository.findByDetailId(id);
    }

    @Override
    public List<ConstDetailQuantity> findByConstID(Long id) {
        return cdqRepository.findByConstructionId(id);
    }
}
