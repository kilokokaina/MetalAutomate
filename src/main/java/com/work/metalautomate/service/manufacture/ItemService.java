package com.work.metalautomate.service.manufacture;

import com.work.metalautomate.model.manufacture.Item;

public interface ItemService {
    void save(Item item);
    Item findById(Long id);
    Item findByName(String itemName);
    void deleteById(Long id);
}
