package com.example.awesomepizza.model;

import com.example.awesomepizza.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity @Table(name = "Orders")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer orderNumber;

    @Enumerated(EnumType.STRING)
    @Column(length = 12, columnDefinition = "varchar(12) default 'CREATED'")
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @NonNull
    private Customer customer;

    @ManyToMany
    @JoinTable(
            name = "Orders_Pizzas",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "pizza_id")
    )
    @NonNull
    private Set<Pizza> pizzas;
}
