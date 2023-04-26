package com.work.metalautomate.service.impl;

import com.work.metalautomate.model.Item;
import com.work.metalautomate.repo.ItemRepository;
import com.work.metalautomate.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {
    public final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public void save(Item item) {
        itemRepository.save(item);
    }

    @Override
    public Item findById(Long id) {
        return itemRepository.findById(id).isPresent() ? itemRepository.findById(id).get() : null;
    }

    @Override
    public Item findByName(String itemName) {
        return itemRepository.findByItemName(itemName);
    }

    @Override
    public void deleteById(Long id) {
        itemRepository.deleteById(id);
    }
}
