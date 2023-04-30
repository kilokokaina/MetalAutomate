package com.work.metalautomate.service.impl;

import com.work.metalautomate.model.quantity.ItemDetailQuantity;
import com.work.metalautomate.repo.IDQRepository;
import com.work.metalautomate.service.IDQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IDQServiceImpl implements IDQService {
    private final IDQRepository idqRepository;

    @Autowired
    public IDQServiceImpl(IDQRepository idqRepository) {
        this.idqRepository = idqRepository;
    }

    @Override
    public void save(ItemDetailQuantity itemDetailQuantity) {
        idqRepository.save(itemDetailQuantity);
    }

    @Override
    public ItemDetailQuantity findByID(Long id) {
        return idqRepository.findById(id).isPresent() ? idqRepository.findById(id).get() : null;
    }

    @Override
    public ItemDetailQuantity findByDetailID(Long id) {
        return idqRepository.findByDetailId(id);
    }

    @Override
    public List<ItemDetailQuantity> findByItemID(Long id) {
        return idqRepository.findByItemId(id);
    }
}
