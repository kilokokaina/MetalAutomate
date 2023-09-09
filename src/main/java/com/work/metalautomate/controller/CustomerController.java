package com.work.metalautomate.controller;

import com.work.metalautomate.model.manufacture.Detail;
import com.work.metalautomate.model.manufacture.Element;
import com.work.metalautomate.model.manufacture.Item;
import com.work.metalautomate.model.order.OrderModel;
import com.work.metalautomate.service.impl.manufacture.DetailServiceImpl;
import com.work.metalautomate.service.impl.manufacture.ItemServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("customer")
public class CustomerController {
    private final ItemServiceImpl itemService;
    private final DetailServiceImpl detailService;

    private final OrderModel globalOrder;

    @Autowired
    public CustomerController(ItemServiceImpl itemService, DetailServiceImpl detailService,
                              OrderModel globalOrder) {
        this.detailService = detailService;
        this.itemService = itemService;
        this.globalOrder = globalOrder;
    }

    @GetMapping()
    public String catalog(@RequestParam(value = "detailCheck", required = false) String detailCheck,
                          @RequestParam(value = "itemCheck", required = false) String itemCheck,
                          Model model) {
        List<Element> elementList = new ArrayList<>();

        if (detailCheck != null) elementList.addAll(detailService.detailRepository.findAll());
        if (itemCheck != null) elementList.addAll(itemService.itemRepository.findAll());

        model.addAttribute("elements", elementList);
        model.addAttribute("detail_check", detailCheck);
        model.addAttribute("item_check", itemCheck);

        return "customer/catalog";
    }

    @GetMapping("set_elements")
    public @ResponseBody String setToOrder(@RequestParam String type, @RequestParam Long id) {
        switch (type) {
            case "item" -> {
                List<Item> itemList = globalOrder.getItemList();
                Item item = itemService.findById(id);

                if (itemList != null) {
                    itemList.add(item);
                    globalOrder.setItemList(itemList);
                } else {
                    List<Item> newItemList = new ArrayList<>();
                    newItemList.add(item);

                    globalOrder.setItemList(newItemList);
                }

                return item.getItemName();
            }
            case "detail" -> {
                List<Detail> detailList = globalOrder.getDetailList();
                Detail detail = detailService.findById(id);

                if (detailList != null) {
                    detailList.add(detail);
                    globalOrder.setDetailList(detailList);
                } else {
                    List<Detail> newDetailList = new ArrayList<>();
                    newDetailList.add(detail);

                    globalOrder.setDetailList(newDetailList);
                }

                return detail.getDetailName();
            }
        }

        return "Something went wrong";
    }

    @PostMapping("set_details")
    public @ResponseBody boolean setDetails(@RequestBody String orderDetail) {
        if (orderDetail.isEmpty()) return false;
        globalOrder.setOrderText(orderDetail);

        return true;
    }

    @GetMapping("cart")
    public String checkOrder(Model model) {
        if (globalOrder.getOrderText() != null) {
            model.addAttribute("orderText", globalOrder.getOrderText());
        } else {
            model.addAttribute("orderText", "Пусто");
        }

        model.addAttribute("order", globalOrder);

        return "customer/cart";
    }
}
