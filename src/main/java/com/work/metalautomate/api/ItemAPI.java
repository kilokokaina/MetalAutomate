package com.work.metalautomate.api;

import com.work.metalautomate.model.manufacture.Detail;
import com.work.metalautomate.model.manufacture.Item;
import com.work.metalautomate.service.impl.manufacture.ItemServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/item")
public class ItemAPI {
    private final ItemServiceImpl itemService;

    @Autowired
    public ItemAPI(ItemServiceImpl itemService) {
        this.itemService = itemService;
    }

    @PostMapping("search")
    public List<Item> searchItem(@RequestBody String itemSearchString) {
        if (itemSearchString.contains("(") && itemSearchString.contains(")")) {
            String[] itemInfo = itemSearchString.split(" \\(");

            String itemName = itemInfo[0];
            String itemDescribe = itemInfo[1].replace(")", "");

            List<Item> itemList = itemService.findSeveralByName(itemName);
            return itemList.stream().filter(item ->
                    item.getItemDescribe().equals(itemDescribe)).toList();
        }
        else return itemService.findSeveralByName(itemSearchString);
    }
}
