package com.example.awesomepizza.dto;

import com.example.awesomepizza.enums.PizzaSizeEnum;
import lombok.Data;

@Data
public class PizzaDto {
    private long id;

    private String name;

    private PizzaSizeEnum size;

    private double price;

    // private Set<Order> orders;
}
