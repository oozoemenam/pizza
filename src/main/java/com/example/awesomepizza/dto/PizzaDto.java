package com.example.awesomepizza.dto;

import com.example.awesomepizza.enums.PizzaSize;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PizzaDto {
    private Long id;

    private String name;

    private PizzaSize size;

    private BigDecimal price;
}
