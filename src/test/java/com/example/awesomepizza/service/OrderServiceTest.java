package com.example.awesomepizza.service;

import com.example.awesomepizza.domain.Customer;
import com.example.awesomepizza.domain.Order;
import com.example.awesomepizza.domain.Pizza;
import com.example.awesomepizza.enums.OrderStatusEnum;
import com.example.awesomepizza.enums.PizzaSizeEnum;
import com.example.awesomepizza.exception.NotFoundException;
import com.example.awesomepizza.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class OrderServiceTest {
    @Autowired
    private OrderRepository orderRepository;
    private OrderService orderService;

    Customer customer = new Customer(1, "Sofia", "sofia@email.com", new HashSet<>());
    Pizza pizza = new Pizza(1, "Margherita", PizzaSizeEnum.medium, 6.00, new HashSet<>());
    Order order = new Order();

    @BeforeEach
    public void setup() {
        order.setOrderNumber(1);
        order.setOrderStatus(OrderStatusEnum.received);
        order.setCustomer(customer);
        order.setPizzas(new HashSet<Pizza>() {{ add(pizza); }});
        // order.setPizzas(new HashSet<Pizza>(Arrays.asList(pizza)));

        orderService = new OrderService(orderRepository);
    }

    @Test
    public void shouldGetOrders() {
        orderService.createOrder(order);

        List<Order> orderList = orderService.getOrders();
        Order savedOrder = orderList.iterator().next();

        assertThat(savedOrder).isNotNull();
    }

    @Test
    public void shouldGetOrder() {
        Order savedOrder = orderService.createOrder(order);

        Order foundOrder = orderService.getOrder(savedOrder.getId());

        assertThat(savedOrder).isEqualTo(foundOrder);
    }

    @Test
    public void shouldGetOrdersOrderedByOrderNumberAscending() {
        orderService.createOrder(order);

        List<Order> orderList = orderService.getOrdersPending();
        Order savedOrder = orderList.iterator().next();

        assertThat(savedOrder).isNotNull();
    }

    @Test
    public void shouldCreateOrder() {
        orderService.createOrder(order);

        List<Order> orderList = orderService.getOrders();
        Order savedOrder = orderList.iterator().next();

        assertThat(order).isEqualTo(savedOrder);
    }

    @Test
    public void shouldUpdateOrder() {
        Order savedOrder = orderService.createOrder(order);
        savedOrder.setOrderStatus(OrderStatusEnum.completed);
        orderService.updateOrder(savedOrder.getId(), savedOrder);

        Order foundOrder = orderService.getOrder(savedOrder.getId());

        assertThat(foundOrder.getOrderStatus()).isEqualTo(OrderStatusEnum.completed);
    }

    @Test
    public void shouldDeleteOrder() {
        assertThrows(NotFoundException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Order savedOrder = orderService.createOrder(order);

                orderService.deleteOrder(savedOrder.getId());
                Order foundOrder = orderService.getOrder(savedOrder.getId());

                assertThat(foundOrder).isNull();
            }
        });
    }
}