package com.example.awesomepizza.dto;

import com.example.awesomepizza.enums.OrderStatusEnum;
import lombok.Data;

import java.util.List;

@Data
public class OrderPendingDto {
    private int orderNumber;

    private OrderStatusEnum orderStatus;

    private List<PizzaDto> pizzas;
}
