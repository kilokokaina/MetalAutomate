package com.work.metalautomate.controller;

import com.work.metalautomate.api.dto.CredentialsDTO;
import com.work.metalautomate.model.manufacture.Detail;
import com.work.metalautomate.model.manufacture.Item;
import com.work.metalautomate.model.user.Role;
import com.work.metalautomate.model.user.UserModel;
import com.work.metalautomate.service.impl.AuthenticationProviderImpl;
import com.work.metalautomate.service.impl.manufacture.DetailServiceImpl;
import com.work.metalautomate.service.impl.manufacture.ItemServiceImpl;
import com.work.metalautomate.service.impl.user.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Slf4j
@Controller
public class MainController {
    private final AuthenticationProviderImpl authenticationProvider;
    private final DetailServiceImpl detailService;
    private final ItemServiceImpl itemService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    public MainController(DetailServiceImpl detailService, ItemServiceImpl itemService,
                          AuthenticationProviderImpl authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
        this.detailService = detailService;
        this.itemService = itemService;
    }

    @GetMapping("front")
    public String frontTest() {
        return "front";
    }

    @PostMapping("search")
    public String search(@RequestParam(name = "query") String search, Model model) {
        log.info(search);

        List<Detail> detailList = detailService.findSeveralByName(search);
        List<Item> itemList = itemService.findSeveralByName(search);

        model.addAttribute("detailList", detailList);
        model.addAttribute("itemList", itemList);

        return "result";
    }

    @PostMapping("auth")
    public @ResponseBody String auth(@RequestBody CredentialsDTO credentialsDTO) {
        return authenticationProvider.startSession(credentialsDTO);
    }

    @GetMapping("get_context")
    public @ResponseBody String getContext() {
        log.info(Thread.currentThread().getName());
        return SecurityContextHolder.getContext().toString();
    }

    @PostMapping("reg")
    public @ResponseBody String register(@RequestBody CredentialsDTO userData) {
        UserModel newUser = new UserModel();

        newUser.setUsername(userData.getUsername());
        newUser.setPassword(userData.getPassword());
        newUser.setRoleSet(Set.of(Role.USER));

        userService.save(newUser);

        return String.format("User %s created", newUser.getUsername());
    }
}
