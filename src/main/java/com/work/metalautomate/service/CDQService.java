package com.work.metalautomate.service;

import com.work.metalautomate.model.Construction;
import com.work.metalautomate.model.Detail;
import com.work.metalautomate.model.quantity.ConstDetailQuantity;

import java.util.List;

public interface CDQService {
    void save(ConstDetailQuantity constDetailQuantity);
    ConstDetailQuantity findByID(Long id);
    ConstDetailQuantity findByDetailID(Long id);
    List<ConstDetailQuantity> findByConstID(Long id);
}
