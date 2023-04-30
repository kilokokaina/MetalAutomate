package com.work.metalautomate;

import com.work.metalautomate.model.quantity.ConstDetailQuantity;
import com.work.metalautomate.model.Construction;
import com.work.metalautomate.model.Detail;
import com.work.metalautomate.repo.CDQRepository;
import com.work.metalautomate.service.impl.CDQServiceImpl;
import com.work.metalautomate.service.impl.ConstructionServiceImpl;
import com.work.metalautomate.service.impl.DetailServiceImpl;
import com.work.metalautomate.service.impl.ItemServiceImpl;
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
    public final CDQServiceImpl cdqService;

    @Autowired
    public MetalAutomateApplication(CDQServiceImpl cdqService) {
        this.cdqService = cdqService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MetalAutomateApplication.class, args);
    }

    @Bean
    public CommandLineRunner cmd() {
        return args -> {
            List<ConstDetailQuantity> cdqList = cdqService.findByConstID(1L);
            cdqList.forEach(cdqItem -> {
                Detail detail = cdqItem.getDetail();
                log.info(detail.getDetailName() + " " + cdqItem.getQuantity());
            });
        };
    }
}
