package com.work.metalautomate.api;

import com.work.metalautomate.api.dto.OrderDTO;
import com.work.metalautomate.model.order.OrderModel;
import com.work.metalautomate.model.order.OrderStatus;
import com.work.metalautomate.service.impl.order.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/order")
public class OrderAPI {
    private final OrderServiceImpl orderService;

    @Autowired
    public OrderAPI(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @PostMapping("create")
    public @ResponseBody String getOrder(@RequestBody OrderDTO orderDTO) {
        OrderModel orderModel = new OrderModel();

        orderModel.setDetailList(List.of(orderDTO.getDetailList()));
        orderModel.setItemList(List.of(orderDTO.getItemList()));
        orderModel.setOrderText(orderDTO.getOrderDetails());
        orderModel.setOrderStatus(OrderStatus.ACCEPTED);

        log.info(orderModel.toString());

        orderService.save(orderModel);

        return String.format("Order %s created", orderModel.getCreationDate());
    }

    @GetMapping("set_status")
    public @ResponseBody String setOrderStatus(@RequestParam String orderStatus,
                                               @RequestParam OrderModel orderModel) {
        orderModel.setOrderStatus(OrderStatus.valueOf(orderStatus));
        orderService.save(orderModel);

        return "Статус заказа изменен";
    }

    @GetMapping("accepted_list")
    public @ResponseBody List<OrderModel> getAcceptedOrders() {
        return orderService.findAll().stream().filter(order ->
                order.getOrderStatus().name().equals("IN_WORK")).toList();
    }
}
