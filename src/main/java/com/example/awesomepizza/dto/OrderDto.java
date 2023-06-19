package com.example.awesomepizza.dto;

import com.example.awesomepizza.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderDto {
    private Long id;

    private Integer orderNumber;

    private OrderStatus orderStatus;

    private CustomerDto customer;

    private List<PizzaDto> pizzas;

    @JsonProperty("totalPrice")
    private BigDecimal getTotalPrice() {
        BigDecimal sum = BigDecimal.ZERO;
        for (PizzaDto pizza : pizzas) {
            sum = sum.add(pizza.getPrice());
        }
        return sum;
    };
}
