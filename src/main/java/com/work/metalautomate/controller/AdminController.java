package com.work.metalautomate.controller;

import com.work.metalautomate.model.order.OrderModel;
import com.work.metalautomate.model.order.OrderStatus;
import com.work.metalautomate.service.impl.order.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
public class AdminController {
    private final OrderServiceImpl orderService;

    @Autowired
    public AdminController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @GetMapping("admin")
    public String admin(Model model) {
        model.addAttribute("orderList", orderService.findAll());
        return "orders";
    }

    @GetMapping("order/{id}")
    public String order(@PathVariable(value = "id") OrderModel orderModel, Model model) {
        model.addAttribute("statusList", OrderStatus.values());
        model.addAttribute("order", orderModel);
        return "test_order";
    }
}
