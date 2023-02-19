package com.example.awesomepizza.repository;

import com.example.awesomepizza.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCompleted(boolean completed);
}
