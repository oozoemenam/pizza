package com.example.awesomepizza.repository;

import com.example.awesomepizza.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByOrderByOrderNumberAsc();
}
