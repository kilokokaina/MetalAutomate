package com.work.metalautomate.controller;

import com.work.metalautomate.model.manufacture.Construction;
import com.work.metalautomate.model.manufacture.Detail;
import com.work.metalautomate.model.manufacture.Item;
import com.work.metalautomate.service.impl.manufacture.ConstructionServiceImpl;
import com.work.metalautomate.service.impl.manufacture.DetailServiceImpl;
import com.work.metalautomate.service.impl.manufacture.ItemServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
public class MainController {
    private final ConstructionServiceImpl constructionService;
    private final DetailServiceImpl detailService;
    private final ItemServiceImpl itemService;

    @Autowired
    public MainController(DetailServiceImpl detailService, ItemServiceImpl itemService,
            ConstructionServiceImpl constructionService) {
        this.constructionService = constructionService;
        this.detailService = detailService;
        this.itemService = itemService;
    }

    @GetMapping
    public String hello() {
        return "index";
    }

    @PostMapping("search")
    public String search(@RequestParam(name = "search") String search, Model model) {
        log.info(search);

        List<Construction> constList = constructionService.findSeveralByName(search);
        List<Detail> detailList = detailService.findSeveralByName(search);
        List<Item> itemList = itemService.findSeveralByName(search);

        model.addAttribute("constList", constList);
        model.addAttribute("detailList", detailList);
        model.addAttribute("itemList", itemList);

        return "result";
    }
}
