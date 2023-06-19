package com.example.awesomepizza.service;

import com.example.awesomepizza.enums.OrderStatus;
import com.example.awesomepizza.exception.NotFoundException;
import com.example.awesomepizza.model.Order;
import com.example.awesomepizza.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    private Order findOrThrow(Long id) {
        return orderRepository.findById(id).orElseThrow(
                () -> new NotFoundException(
                        "Order with id " + id + " was not found")
        );
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersPending() {
        return orderRepository.findByOrderStatus(OrderStatus.CREATED);
    }

    public Order getOrder(Long id) {
        return findOrThrow(id);
    }

    public Order getOrderByOrderNumber(Integer orderNumber) {
        return orderRepository.findByOrderNumber(orderNumber);
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, Order order) {
        findOrThrow(id);
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        findOrThrow(id);
        orderRepository.deleteById(id);
    }
}
