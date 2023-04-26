package com.work.metalautomate.api;

import com.work.metalautomate.model.Construction;
import com.work.metalautomate.service.impl.ConstructionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/const")
public class ConstructionAPI {
    private final ConstructionServiceImpl constructionService;

    @Autowired
    public ConstructionAPI(ConstructionServiceImpl constructionService) {
        this.constructionService = constructionService;
    }

    @GetMapping
    public List<Construction> getAll() {
        return constructionService.constructionRepository.findAll();
    }

    @GetMapping("{id}")
    public Construction getConstByID(@PathVariable(value = "id") Construction construction) {
        return construction;
    }
}
