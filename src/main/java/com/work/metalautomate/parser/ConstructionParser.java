package com.work.metalautomate.parser;

import com.work.metalautomate.model.Construction;
import com.work.metalautomate.model.Detail;
import com.work.metalautomate.model.Item;
import com.work.metalautomate.service.impl.ConstructionServiceImpl;
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
public class ConstructionParser implements CommandLineRunner {
    private final ConstructionServiceImpl constructionService;
    private final DetailServiceImpl detailService;
    private final ItemServiceImpl itemService;

    @Autowired
    public ConstructionParser(ConstructionServiceImpl constructionService,
                              DetailServiceImpl detailService, ItemServiceImpl itemService) {
        this.constructionService = constructionService;
        this.detailService = detailService;
        this.itemService = itemService;
    }

    @Override
    public void run(String... args) {
        ItemParser itemParser = new ItemParser(detailService, itemService);
        File constDir = new File("/Users/nikol/Desktop/details/construction");

        for (File construction : Objects.requireNonNull(constDir.listFiles(File::isFile))) {
            String[] constText = construction.getName().replace(".txt", "").split(" ");

            String constName = constText[0].replace("_", " ");
            String constDescribe = constText[1].replace("_", " ");

            Construction constModel = new Construction();

            constModel.setConstName(constName);
            constModel.setConstDescribe(constDescribe);

            List<Item> itemList = new ArrayList<>();
            List<Detail> detailList = new ArrayList<>();

            try(BufferedReader bufferedReader = new BufferedReader(new FileReader(construction))) {
                String line;

                while (!Objects.equals(line = bufferedReader.readLine(), "")) {
                    detailList.add(itemParser.getDetail(line));
                }

                while((line = bufferedReader.readLine()) != null) {
                    String itemName = line.split(" ")[0].replace("_", " ");
                    itemList.add(itemService.findByName(itemName));
                }

            } catch (IOException ignored) {}

            constModel.setDetailList(detailList);
            constModel.setItemList(itemList);

            constructionService.save(constModel);

            log.info(constModel.toString());
        }
    }
}
