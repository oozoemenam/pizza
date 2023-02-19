package com.example.awesomepizza.dto;

import lombok.Data;

import java.util.Set;

@Data
public class OrderDto {
    private long id;

    private CustomerDto customer;

    private Set<PizzaDto> pizzas;

    private double totalPrice;

    private boolean completed;
}
