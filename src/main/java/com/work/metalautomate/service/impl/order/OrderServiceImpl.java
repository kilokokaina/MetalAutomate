package com.work.metalautomate.service.impl.order;

import com.work.metalautomate.model.order.OrderModel;
import com.work.metalautomate.repo.order.OrderRepository;
import com.work.metalautomate.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderModel save(OrderModel orderModel) {
        orderRepository.save(orderModel);
        return orderModel;
    }

    @Override
    public void delete(Long orderID) {
        orderRepository.deleteById(orderID);
    }

    @Override
    public OrderModel findByID(Long orderID) {
        return orderRepository.findById(orderID).isPresent() ? orderRepository.findById(orderID).get() : null;
    }

    @Override
    public List<OrderModel> findAll() {
        return orderRepository.findAll();
    }
}
