package com.work.metalautomate.parser;

import com.work.metalautomate.model.Detail;
import com.work.metalautomate.model.Item;
import com.work.metalautomate.service.impl.DetailServiceImpl;
import com.work.metalautomate.service.impl.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
public class ItemParser /*implements CommandLineRunner*/ {
    private final DetailServiceImpl detailService;
    private final ItemServiceImpl itemService;

    @Autowired
    public ItemParser(DetailServiceImpl detailService, ItemServiceImpl itemService) {
        this.detailService = detailService;
        this.itemService = itemService;
    }

    public Detail getDetail(String line) {
        String[] detailText = line.split(" ");

        String detailDescribe = "";
        String detailName = detailText[0].replace("_", " ");

        if (detailText.length == 2) detailDescribe = detailText[1].replace("_", " ");

        List<Detail> detailCompare = detailService.findSeveralByName(detailName);
        boolean createNewDetail = true;

        Detail detailModel = null;

        if (detailCompare.size() != 0) {
            for (Detail detail : detailCompare) {
                if (detail.getDetailDescribe().equals(detailDescribe)) {
                    detailModel = detail;
                    createNewDetail = false;
                    break;
                }
            }
        }

        if (createNewDetail) {
            detailModel = new Detail(detailName, detailDescribe);
            detailService.save(detailModel);
        }

        return detailModel;
    }

//    @Override
    public void run(String... args) {
        File itemDir = new File("/Users/nikol/Desktop/details/item");

        for (File item : Objects.requireNonNull(itemDir.listFiles(File::isFile))) {
            String itemText = item.getName().replace(".txt", "");

            String itemName = itemText.split(" ")[0].replace("_", " ");
            String itemDescribe = itemText.split(" ")[1].replace("_", " ");

            Item itemModel = new Item();
            itemModel.setItemName(itemName);
            itemModel.setItemDescribe(itemDescribe);

            List<Detail> detailList = new ArrayList<>();

            try(BufferedReader bufferedReader = new BufferedReader(new FileReader(item))) {
                String line;

                while((line = bufferedReader.readLine()) != null) {
                    detailList.add(getDetail(line));
                }

            } catch (IOException ignored) {}

            itemModel.setDetailList(detailList);
            itemService.save(itemModel);
        }
    }
}
