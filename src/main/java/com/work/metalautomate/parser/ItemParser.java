package com.work.metalautomate.parser;

import com.work.metalautomate.model.Detail;
import com.work.metalautomate.model.Item;
import com.work.metalautomate.model.quantity.ItemDetailQuantity;
import com.work.metalautomate.service.impl.DetailServiceImpl;
import com.work.metalautomate.service.impl.IDQServiceImpl;
import com.work.metalautomate.service.impl.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Slf4j
@Component
public class ItemParser implements CommandLineRunner {
    private final DetailServiceImpl detailService;
    private final ItemServiceImpl itemService;
    private final IDQServiceImpl idqService;

    @Autowired
    public ItemParser(DetailServiceImpl detailService, ItemServiceImpl itemService,
                      IDQServiceImpl idqService) {
        this.detailService = detailService;
        this.itemService = itemService;
        this.idqService = idqService;
    }

    public ElementInfo getElementInfo(String line) {
        String[] detailText = line.split(" ");

        String detailDescribe = "";
        String detailName = detailText[0].replace("_", " ");
        int detailQuantity;

        if (detailText.length == 3) {
            detailDescribe = detailText[1].replace("_", " ");
            detailQuantity = Integer.parseInt(detailText[2]);
        } else detailQuantity = Integer.parseInt(detailText[1]);

        return new ElementInfo(detailName, detailDescribe, detailQuantity);
    }

    public Pair<Detail> getDetail(String line) {
        ElementInfo detailInfo = getElementInfo(line);

        List<Detail> detailCompare = detailService.findSeveralByName(detailInfo.getElementName());
        boolean createNewDetail = true;

        Detail detailModel = null;

        if (detailCompare.size() != 0) {
            for (Detail detail : detailCompare) {
                if (detail.getDetailDescribe().equals(detailInfo.getElementDescribe())) {
                    detailModel = detail;
                    createNewDetail = false;
                    break;
                }
            }
        }

        if (createNewDetail) {
            detailModel = new Detail(detailInfo.getElementName(), detailInfo.getElementDescribe());
            detailService.save(detailModel);
        }

        return new Pair<>(detailModel, detailInfo.getElementQuantity());
    }

    @Override
    public void run(String... args) {
        File itemDir = new File("/Users/nikol/Desktop/details/item");

        for (File item : Objects.requireNonNull(itemDir.listFiles(File::isFile))) {
            String itemText = item.getName().replace(".txt", "");

            if (itemText.split("")[0].equals(".")) continue;

            String itemName = itemText.split(" ")[0].replace("_", " ");
            String itemDescribe = itemText.split(" ")[1].replace("_", " ");

            if (itemService.findByName(itemName) != null) {
                log.info("Item already exists");
                continue;
            }

            Item itemModel = new Item();
            itemModel.setItemName(itemName);
            itemModel.setItemDescribe(itemDescribe);

            itemService.save(itemModel);

            List<Detail> detailList = new ArrayList<>();

            try(BufferedReader bufferedReader = new BufferedReader(new FileReader(item))) {
                String line;

                while((line = bufferedReader.readLine()) != null) {
                    ItemDetailQuantity idqModel = new ItemDetailQuantity();
                    Pair<Detail> pair = getDetail(line);

                    Detail detail = pair.getObject();
                    detailList.add(detail);

                    idqModel.setItem(itemModel);
                    idqModel.setDetail(detail);
                    idqModel.setQuantity(pair.getQuantity());

                    idqService.save(idqModel);
                }

            } catch (IOException ignored) {}

            itemModel.setDetailList(detailList);
            itemService.save(itemModel);
        }
    }
}
