package com.work.metalautomate.api;

import com.work.metalautomate.api.dto.OrderDTO;
import com.work.metalautomate.model.manufacture.Detail;
import com.work.metalautomate.model.manufacture.Item;
import com.work.metalautomate.model.order.OrderModel;
import com.work.metalautomate.model.order.OrderStatus;
import com.work.metalautomate.service.impl.manufacture.DetailServiceImpl;
import com.work.metalautomate.service.impl.manufacture.ItemServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/order")
public class OrderAPI {
    private final DetailServiceImpl detailService;
    private final ItemServiceImpl itemService;

    @Autowired
    public OrderAPI(DetailServiceImpl detailService, ItemServiceImpl itemService) {
        this.detailService = detailService;
        this.itemService = itemService;
    }

    @PostMapping("create")
    public @ResponseBody String getOrder(@RequestBody OrderDTO orderDTO) {
        OrderModel orderModel = new OrderModel();

        List<Detail> detailList = new ArrayList<>();
        Arrays.stream(orderDTO.getDetailList()).forEach(detail ->
                detailList.add(detailService.findByName(detail))
        );

        List<Item> itemList = new ArrayList<>();
        Arrays.stream(orderDTO.getItemList()).forEach(item ->
                itemList.add(itemService.findByName(item))
        );

        orderModel.setDetailList(detailList);
        orderModel.setItemList(itemList);
        orderModel.setOrderText(orderDTO.getOrderDetails());
        orderModel.setOrderStatus(OrderStatus.ACCEPTED);

        log.info(orderModel.toString());

        return "Order created";
    }

    @PostMapping("test")
    public void test(@RequestBody Detail[] details) {
        log.info(List.of(details).toString());
    }

    @PostMapping("test_item")
    public void testItem(@RequestBody Item[] items) {
        log.info(List.of(items).toString());
    }
}
