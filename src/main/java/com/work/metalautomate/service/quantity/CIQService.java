package com.work.metalautomate.service.quantity;

import com.work.metalautomate.model.quantity.ConstItemQuantity;

import java.util.List;

public interface CIQService {
    void save(ConstItemQuantity constItemQuantity);
    ConstItemQuantity findByID(Long id);
    ConstItemQuantity findByItemID(Long id);
    List<ConstItemQuantity> findByConstID(Long id);
}
