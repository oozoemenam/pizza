package com.example.awesomepizza.service;

import com.example.awesomepizza.exception.NotFoundException;
import com.example.awesomepizza.domain.Pizza;
import com.example.awesomepizza.repository.PizzaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PizzaService {
    private final PizzaRepository pizzaRepository;

    private Pizza findOrThrow(long id) {
        return pizzaRepository.findById(id).orElseThrow(
                () -> new NotFoundException(
                        "Pizza with id " + id + " was not found")
        );
    }

    public List<Pizza> getPizzas() {
        return pizzaRepository.findAll();
    }

    public List<Pizza> getPizzasByName(String name) {
        return pizzaRepository.findByNameContaining(name);
    }
    
    public Pizza getPizzaById(long id) {
        return findOrThrow(id);
    }

    public Pizza createPizza(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    public Pizza updatePizza(long id, Pizza pizza) {
        findOrThrow(id);
        return pizzaRepository.save(pizza);
    }

    public void deletePizza(long id) {
        findOrThrow(id);
        pizzaRepository.deleteById(id);
    }
}
