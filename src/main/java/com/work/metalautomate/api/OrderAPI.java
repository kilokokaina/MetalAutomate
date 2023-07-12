package com.work.metalautomate.api;

import com.work.metalautomate.api.dto.OrderDTO;
import com.work.metalautomate.model.order.OrderModel;
import com.work.metalautomate.model.order.OrderStatus;
import com.work.metalautomate.repo.order.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/order")
public class OrderAPI {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderAPI(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PostMapping("create")
    public @ResponseBody String getOrder(@RequestBody OrderDTO orderDTO) {
        OrderModel orderModel = new OrderModel();

        orderModel.setDetailList(List.of(orderDTO.getDetailList()));
        orderModel.setItemList(List.of(orderDTO.getItemList()));
        orderModel.setOrderText(orderDTO.getOrderDetails());
        orderModel.setOrderStatus(OrderStatus.ACCEPTED);

        log.info(orderModel.toString());

        orderRepository.save(orderModel);

        return String.format("Order %s created", orderModel.getCreationDate());
    }
}
