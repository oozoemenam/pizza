package com.example.awesomepizza.domain;

import com.example.awesomepizza.enums.PizzaSizeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "Pizzas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @Enumerated(EnumType.STRING)
    @Column(length = 6, columnDefinition = "varchar(6) default 'medium'")
    private PizzaSizeEnum size;

    private double price;

    @ManyToMany(mappedBy = "pizzas")
    private Set<Order> orders;
}
