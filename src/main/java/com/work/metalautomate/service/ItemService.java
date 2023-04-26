package com.work.metalautomate.service;

import com.work.metalautomate.model.Item;

public interface ItemService {
    void save(Item item);
    Item findById(Long id);
    Item findByName(String itemName);
    void deleteById(Long id);
}
