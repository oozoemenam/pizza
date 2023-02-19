package com.example.awesomepizza.repository;

import com.example.awesomepizza.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {
    List<Pizza> findByNameContaining(String name);
}
