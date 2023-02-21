package com.example.awesomepizza.service;

import com.example.awesomepizza.exception.NotFoundException;
import com.example.awesomepizza.domain.Order;
import com.example.awesomepizza.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    private Order findOrThrow(long id) {
        return orderRepository.findById(id).orElseThrow(
                () -> new NotFoundException(
                        "Order with id " + id + " was not found")
        );
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersPending() {
        return orderRepository.findByOrderByOrderNumberAsc();
    }

    public Order getOrder(long id) {
        return findOrThrow(id);
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrder(long id, Order order) {
        findOrThrow(id);
        return orderRepository.save(order);
    }

    public void deleteOrder(long id) {
        findOrThrow(id);
        orderRepository.deleteById(id);
    }
}
