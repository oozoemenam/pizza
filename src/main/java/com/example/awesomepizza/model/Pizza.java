package com.example.awesomepizza.model;

import com.example.awesomepizza.enums.PizzaSize;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity @Table(name = "Pizzas")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(length = 6, columnDefinition = "varchar(6) default 'MEDIUM'")
    private PizzaSize size;

    @NonNull
    private BigDecimal price;

    @ManyToMany(mappedBy = "pizzas")
    private Set<Order> orders = new HashSet<>();
}
