package com.example.awesomepizza.domain;

import com.example.awesomepizza.enums.OrderStatusEnum;
import com.example.awesomepizza.enums.PizzaSizeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "Orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int orderNumber;

    @Enumerated(EnumType.STRING)
    @Column(length = 12, columnDefinition = "varchar(12) default 'received'")
    private OrderStatusEnum orderStatus;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany
    @JoinTable(
            name = "Orders_Pizzas",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "pizza_id")
    )
    private Set<Pizza> pizzas;

    private double totalPrice;
}
