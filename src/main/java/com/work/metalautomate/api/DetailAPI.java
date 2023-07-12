package com.work.metalautomate.api;

import com.work.metalautomate.model.manufacture.Detail;
import com.work.metalautomate.service.impl.manufacture.DetailServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/detail")
public class DetailAPI {
    private final DetailServiceImpl detailService;

    @Autowired
    public DetailAPI(DetailServiceImpl detailService) {
        this.detailService = detailService;
    }

    @GetMapping
    public List<Detail> getAll() {
        return detailService.detailRepository.findAll();
    }

    @GetMapping("{id}")
    public Detail getByID(@PathVariable(value = "id") Detail detail) {
        return detail;
    }

    @PostMapping("search")
    public List<Detail> searchDetail(@RequestBody String detailSearchString) {
        if (detailSearchString.contains("(") && detailSearchString.contains(")")) {
            String[] detailInfo = detailSearchString.split(" \\(");

            String detailName = detailInfo[0];
            String detailDescribe = detailInfo[1].replace(")", "");

            List<Detail> detailList = detailService.findSeveralByName(detailName);
            return detailList.stream().filter(detail ->
                    detail.getDetailDescribe().equals(detailDescribe)).toList();
        }
        else return detailService.findSeveralByName(detailSearchString);
    }
}
