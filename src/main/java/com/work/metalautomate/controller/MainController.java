package com.work.metalautomate.controller;

import com.work.metalautomate.api.dto.CredentialsDTO;
import com.work.metalautomate.model.manufacture.Construction;
import com.work.metalautomate.model.manufacture.Detail;
import com.work.metalautomate.model.manufacture.Item;
import com.work.metalautomate.service.impl.AuthenticationProviderImpl;
import com.work.metalautomate.service.impl.manufacture.ConstructionServiceImpl;
import com.work.metalautomate.service.impl.manufacture.DetailServiceImpl;
import com.work.metalautomate.service.impl.manufacture.ItemServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
public class MainController {
    private final AuthenticationProviderImpl authenticationProvider;
    private final ConstructionServiceImpl constructionService;
    private final DetailServiceImpl detailService;
    private final ItemServiceImpl itemService;

    @Autowired
    public MainController(DetailServiceImpl detailService, ItemServiceImpl itemService,
                          AuthenticationProviderImpl authenticationProvider,
                          ConstructionServiceImpl constructionService) {
        this.authenticationProvider = authenticationProvider;
        this.constructionService = constructionService;
        this.detailService = detailService;
        this.itemService = itemService;
    }

    @GetMapping
    public String hello() {
        return "index";
    }

    @PostMapping("search")
    public String search(@RequestParam(name = "query") String search, Model model) {
        log.info(search);

        List<Construction> constList = constructionService.findSeveralByName(search);
        List<Detail> detailList = detailService.findSeveralByName(search);
        List<Item> itemList = itemService.findSeveralByName(search);

        model.addAttribute("constList", constList);
        model.addAttribute("detailList", detailList);
        model.addAttribute("itemList", itemList);

        return "result";
    }

    @GetMapping("/get_context")
    public @ResponseBody String getContext() {
        return SecurityContextHolder.getContext().toString();
    }

    @PostMapping("/auth")
    public @ResponseBody boolean auth(@RequestBody CredentialsDTO credentialsDTO) {
        boolean loginResult = authenticationProvider.startSession(credentialsDTO);
        log.info(SecurityContextHolder.getContext().toString());

        return loginResult;
    }
}
