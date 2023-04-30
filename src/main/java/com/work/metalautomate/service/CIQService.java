package com.work.metalautomate.service;

import com.work.metalautomate.model.quantity.ConstItemQuantity;

import java.util.List;

public interface CIQService {
    void save(ConstItemQuantity constItemQuantity);
    ConstItemQuantity findByID(Long id);
    ConstItemQuantity findByDetailID(Long id);
    List<ConstItemQuantity> findByItemID(Long id);
}
