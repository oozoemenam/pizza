package com.example.awesomepizza.repository;

import com.example.awesomepizza.model.Order;
import com.example.awesomepizza.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findByOrderNumber(Integer orderNumber);
    List<Order> findByOrderStatus(OrderStatus status);
}
