package com.work.metalautomate.parser;

import com.work.metalautomate.model.manufacture.Construction;
import com.work.metalautomate.model.manufacture.Detail;
import com.work.metalautomate.model.manufacture.Item;
import com.work.metalautomate.model.quantity.ConstDetailQuantity;
import com.work.metalautomate.model.quantity.ConstItemQuantity;
import com.work.metalautomate.service.impl.manufacture.ConstructionServiceImpl;
import com.work.metalautomate.service.impl.manufacture.DetailServiceImpl;
import com.work.metalautomate.service.impl.manufacture.ItemServiceImpl;
import com.work.metalautomate.service.impl.quantity.CDQServiceImpl;
import com.work.metalautomate.service.impl.quantity.CIQServiceImpl;
import com.work.metalautomate.service.impl.quantity.IDQServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
public class ConstructionParser /*implements CommandLineRunner*/ {
    private final ConstructionServiceImpl constructionService;
    private final DetailServiceImpl detailService;
    private final ItemServiceImpl itemService;

    private final IDQServiceImpl idqService;
    private final CDQServiceImpl cdqService;
    private final CIQServiceImpl ciqService;

    @Autowired
    public ConstructionParser(ConstructionServiceImpl constructionService,
                              DetailServiceImpl detailService, ItemServiceImpl itemService,
                              IDQServiceImpl idqService, CDQServiceImpl cdqService, CIQServiceImpl ciqService) {
        this.constructionService = constructionService;
        this.detailService = detailService;
        this.itemService = itemService;

        this.idqService = idqService;
        this.cdqService = cdqService;
        this.ciqService = ciqService;
    }

//    @Override
    public void run(String... args) {
        ItemParser itemParser = new ItemParser(detailService, itemService, idqService);
        File constDir = new File("/Users/nikol/Desktop/details/construction");

        for (File construction : Objects.requireNonNull(constDir.listFiles(File::isFile))) {
            String[] constText = construction.getName().replace(".txt", "").split(" ");

            if (constText[0].split("")[0].equals(".")) continue;

            String constName = constText[0].replace("_", " ");
            String constDescribe = constText[1].replace("_", " ");

            if (constructionService.findByName(constName) != null) {
                log.info("Construction already exists");
                continue;
            }

            Construction constModel = new Construction();

            constModel.setConstName(constName);
            constModel.setConstDescribe(constDescribe);

            constructionService.save(constModel);

            List<Item> itemList = new ArrayList<>();
            List<Detail> detailList = new ArrayList<>();

            try(BufferedReader bufferedReader = new BufferedReader(new FileReader(construction))) {
                String line;

                while (!Objects.equals(line = bufferedReader.readLine(), "")) {
                    ConstDetailQuantity cdqModel = new ConstDetailQuantity();
                    Pair<Detail> pairDetail = itemParser.getDetail(line);

                    detailList.add(pairDetail.getObject());

                    cdqModel.setConstruction(constModel);
                    cdqModel.setDetail(pairDetail.getObject());
                    cdqModel.setQuantity(pairDetail.getQuantity());

                    cdqService.save(cdqModel);
                }

                while((line = bufferedReader.readLine()) != null) {
                    ElementInfo itemInfo = itemParser.getElementInfo(line);
                    Item itemModel = itemService.findByName(itemInfo.getElementName());
                    ConstItemQuantity ciqModel = new ConstItemQuantity();

                    itemList.add(itemModel);

                    ciqModel.setConstruction(constModel);
                    ciqModel.setItem(itemModel);
                    ciqModel.setQuantity(itemInfo.getElementQuantity());

                    ciqService.save(ciqModel);
                }

            } catch (IOException ignored) {}

            constModel.setDetailList(detailList);
            constModel.setItemList(itemList);

            constructionService.save(constModel);

            log.info(constModel.toString());
        }
    }
}
