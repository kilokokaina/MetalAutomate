package com.work.metalautomate.parser;

import com.work.metalautomate.service.impl.ConstructionServiceImpl;
import com.work.metalautomate.service.impl.DetailServiceImpl;
import com.work.metalautomate.service.impl.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
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
        File constDir = new File("/Users/nikol/Desktop/details/construction");
        for (File construction : Objects.requireNonNull(constDir.listFiles(File::isFile))) {
            String[] constText = construction.getName().replace(".txt", "").split(" ");

            String constName = constText[0].replace("_", " ");
            String constDescribe = constText[1].replace("_", " ");

            log.info(constName + " " + constDescribe);
        }
    }
}
