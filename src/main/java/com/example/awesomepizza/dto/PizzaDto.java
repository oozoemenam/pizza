package com.example.awesomepizza.dto;

import com.example.awesomepizza.model.Order;
import jakarta.persistence.ManyToMany;

import java.util.Set;

public class PizzaDto {
    private long id;

    private Set<Order> orders;

    private String name;

    private double price;
}
