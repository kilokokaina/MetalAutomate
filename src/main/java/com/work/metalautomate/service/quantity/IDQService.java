package com.work.metalautomate.service.quantity;

import com.work.metalautomate.model.quantity.ItemDetailQuantity;

import java.util.List;

public interface IDQService {
    void save(ItemDetailQuantity itemDetailQuantity);
    ItemDetailQuantity findByID(Long id);
    ItemDetailQuantity findByDetailID(Long id);
    List<ItemDetailQuantity> findByItemID(Long id);
}
