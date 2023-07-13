package com.work.metalautomate.service.order;

import com.work.metalautomate.model.order.OrderModel;

import java.util.List;

public interface OrderService {
    OrderModel save(OrderModel orderModel);
    void delete(Long orderID);
    OrderModel findByID(Long orderID);
    List<OrderModel> findAll();
}
