package com.work.metalautomate;

import com.work.metalautomate.model.Construction;
import com.work.metalautomate.model.Detail;
import com.work.metalautomate.model.Item;
import com.work.metalautomate.model.quantity.ConstDetailQuantity;
import com.work.metalautomate.model.quantity.ConstItemQuantity;
import com.work.metalautomate.model.quantity.ItemDetailQuantity;
import com.work.metalautomate.service.impl.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@Slf4j
@SpringBootApplication
public class MetalAutomateApplication {
    public final IDQServiceImpl idqService;
    public final CIQServiceImpl ciqService;
    public final CDQServiceImpl cdqService;
    public final ItemServiceImpl itemService;
    public final ConstructionServiceImpl constructionService;

    @Autowired
    public MetalAutomateApplication(IDQServiceImpl idqService, ItemServiceImpl itemService,
                                    ConstructionServiceImpl constructionService, CIQServiceImpl ciqService,
                                    CDQServiceImpl cdqService) {
        this.constructionService = constructionService;
        this.itemService = itemService;
        this.idqService = idqService;
        this.ciqService = ciqService;
        this.cdqService = cdqService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MetalAutomateApplication.class, args);
    }

    @Bean
    public CommandLineRunner cmd() {
        return args -> {
            Item itemModel = itemService.findById(2L);
            log.info(itemModel.getItemName());

            List<ItemDetailQuantity> idqList = idqService.findByItemID(2L);
            idqList.forEach(idq -> {
                Detail detail = idq.getDetail();
                log.info(detail.getDetailName() + " " + detail.getDetailDescribe() + " " + idq.getQuantity());
            });

            Construction constModel = constructionService.findById(1L);
            log.info(constModel.getConstName());

            List<ConstItemQuantity> ciqList = ciqService.findByConstID(1L);
            List<ConstDetailQuantity> cdqList = cdqService.findByConstID(1L);

            ciqList.forEach(ciq -> {
                Item item = ciq.getItem();
                log.info(item.getItemName() + " " + item.getItemDescribe() + " " + ciq.getQuantity());
            });

            cdqList.forEach(idq -> {
                Detail detail = idq.getDetail();
                log.info(detail.getDetailName() + " " + detail.getDetailDescribe() + " " + idq.getQuantity());
            });
        };
    }
}
