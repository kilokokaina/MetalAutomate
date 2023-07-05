package com.work.metalautomate.service.impl.manufacture;

import com.work.metalautomate.model.manufacture.Item;
import com.work.metalautomate.repo.manufacture.ItemRepository;
import com.work.metalautomate.service.manufacture.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Item> findSeveralByName(String detailName) {
        return itemRepository.findSeveralByName(detailName);
    }
}
