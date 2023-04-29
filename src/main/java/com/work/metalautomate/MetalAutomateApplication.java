package com.work.metalautomate;

import com.work.metalautomate.model.quantity.ConstDetailQuantity;
import com.work.metalautomate.model.Construction;
import com.work.metalautomate.model.Detail;
import com.work.metalautomate.repo.CDQRepository;
import com.work.metalautomate.service.impl.ConstructionServiceImpl;
import com.work.metalautomate.service.impl.DetailServiceImpl;
import com.work.metalautomate.service.impl.ItemServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class MetalAutomateApplication {
    @Autowired
    public ConstructionServiceImpl constructionService;

    @Autowired
    public DetailServiceImpl detailService;

    @Autowired
    public ItemServiceImpl itemService;

    @Autowired
    public CDQRepository cdqRepository;

    public static void main(String[] args) {
        SpringApplication.run(MetalAutomateApplication.class, args);
    }

    @Bean
    public CommandLineRunner cmd() {
        return args -> {
            Construction construction = constructionService.findByName("Угловая промежуточная опора 2УП10-20МИ-1");
            Detail detail = detailService.findByName("Болт М20x260**");

            ConstDetailQuantity constDetailQuantity = new ConstDetailQuantity(construction, detail, 2);
            cdqRepository.save(constDetailQuantity);
        };
    }
}
