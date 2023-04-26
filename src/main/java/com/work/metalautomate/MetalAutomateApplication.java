package com.work.metalautomate;

import com.work.metalautomate.model.Construction;
import com.work.metalautomate.model.Detail;
import com.work.metalautomate.model.Item;
import com.work.metalautomate.service.impl.ConstructionServiceImpl;
import com.work.metalautomate.service.impl.DetailServiceImpl;
import com.work.metalautomate.service.impl.ItemServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@SpringBootApplication
public class MetalAutomateApplication {
    public ConstructionServiceImpl constructionService;
    public DetailServiceImpl detailService;
    public ItemServiceImpl itemService;

    @Autowired
    public MetalAutomateApplication(ConstructionServiceImpl constructionService,
                                    DetailServiceImpl detailService,
                                    ItemServiceImpl itemService) {
        this.constructionService = constructionService;
        this.detailService = detailService;
        this.itemService = itemService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MetalAutomateApplication.class, args);
    }

    @Bean
    public CommandLineRunner cmd() {
        return args -> {
            Construction construction = constructionService.findByName("const1");

            log.info(construction.getConstName());
            log.info(construction.getConstDescribe());

            construction.getItemList().forEach((key, item) -> {
                log.info(item.getItemName());
                item.getDetailList().forEach((inKey, detail) -> log.info(detail.toString()));
            });

            log.info(construction.toString());
        };
    }
}
