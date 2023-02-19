package com.example.awesomepizza.dto;

import lombok.Data;

import java.util.Set;

@Data
public class CustomerDto {
    private long id;

    private String name;

    private String email;

    // private Set<OrderDto> orders;
}
