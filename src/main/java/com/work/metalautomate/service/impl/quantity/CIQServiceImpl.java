package com.work.metalautomate.service.impl.quantity;

import com.work.metalautomate.model.quantity.ConstItemQuantity;
import com.work.metalautomate.repo.quantity.CIQRepository;
import com.work.metalautomate.service.quantity.CIQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CIQServiceImpl implements CIQService {
    private final CIQRepository ciqRepository;

    @Autowired
    public CIQServiceImpl(CIQRepository ciqRepository) {
        this.ciqRepository = ciqRepository;
    }

    @Override
    public void save(ConstItemQuantity constItemQuantity) {
        ciqRepository.save(constItemQuantity);
    }

    @Override
    public ConstItemQuantity findByID(Long id) {
        return ciqRepository.findById(id).isPresent() ? ciqRepository.findById(id).get() : null;
    }

    @Override
    public ConstItemQuantity findByItemID(Long id) {
        return ciqRepository.findByItemId(id);
    }

    @Override
    public List<ConstItemQuantity> findByConstID(Long id) {
        return ciqRepository.findByConstructionId(id);
    }
}
