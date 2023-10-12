package com.example.awesomepizza.service;

import com.example.awesomepizza.model.Pizza;
import com.example.awesomepizza.enums.PizzaSize;
import com.example.awesomepizza.exception.NotFoundException;
import com.example.awesomepizza.repository.PizzaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class PizzaServiceTest {
    @Autowired
    private PizzaRepository pizzaRepository;
    private PizzaService pizzaService;

    Pizza pizza = new Pizza("Margherita", BigDecimal.valueOf(6.00));

    @BeforeEach
    public void setup() {
        pizzaService = new PizzaService(pizzaRepository);
    }

    @Test
    public void shouldGetPizzas() {
        pizzaService.createPizza(pizza);

        List<Pizza> pizzaList = pizzaService.getPizzas();
        Pizza savedPizza = pizzaList.iterator().next();

        assertThat(savedPizza).isNotNull();
    }

    @Test
    public void shouldGetPizza() {
        Pizza savedPizza = pizzaService.createPizza(pizza);

        Pizza foundPizza = pizzaService.getPizza(savedPizza.getId());

        assertThat(savedPizza).isEqualTo(foundPizza);
    }

    @Test
    public void shouldGetPizzasByName() {
        pizzaService.createPizza(pizza);

        List<Pizza> pizzaList = pizzaService.getPizzasByName("Margherita");
        Pizza savedPizza = pizzaList.iterator().next();

        assertThat(savedPizza).isNotNull();
    }

    @Test
    public void shouldCreatePizza() {
        pizzaService.createPizza(pizza);

        List<Pizza> pizzaList = pizzaService.getPizzas();
        Pizza savedPizza = pizzaList.iterator().next();

        assertThat(pizza).isEqualTo(savedPizza);
    }

    @Test
    public void shouldUpdatePizza() {
        Pizza savedPizza = pizzaService.createPizza(pizza);
        savedPizza.setPrice(BigDecimal.valueOf(7.00));
        pizzaService.updatePizza(savedPizza.getId(), savedPizza);

        Pizza foundPizza = pizzaService.getPizza(savedPizza.getId());

        assertThat(foundPizza.getPrice()).isEqualTo(7.00);
    }

    @Test
    public void shouldDeletePizza() {
        assertThrows(NotFoundException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Pizza savedPizza = pizzaService.createPizza(pizza);

                pizzaService.deletePizza(savedPizza.getId());
                Pizza foundPizza = pizzaService.getPizza(savedPizza.getId());

                assertThat(foundPizza).isNull();
            }
        });
    }
}