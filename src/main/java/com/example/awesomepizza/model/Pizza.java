package com.example.awesomepizza.model;

import com.example.awesomepizza.enums.PizzaSize;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigDecimal;
import java.util.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @NonNull
    private String name;

    @Enumerated(EnumType.STRING)
    // @Column(length = 6, columnDefinition = "varchar(6) default 'MEDIUM'")
    private PizzaSize size = PizzaSize.MEDIUM;

    @Positive
    @Digits(integer = 5, fraction = 2)
    @NonNull
    private BigDecimal price;

    @OneToMany(mappedBy = "pizza")
    @ToString.Exclude
    private List<OrderItem> orderItems = new ArrayList<>();

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Pizza pizza = (Pizza) o;
        return getId() != null && Objects.equals(getId(), pizza.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
