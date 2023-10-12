package com.example.awesomepizza.service;

import com.example.awesomepizza.enums.OrderStatus;
import com.example.awesomepizza.exception.NotFoundException;
import com.example.awesomepizza.model.Customer;
import com.example.awesomepizza.model.Order;
import com.example.awesomepizza.model.OrderItem;
import com.example.awesomepizza.model.Pizza;
import com.example.awesomepizza.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class OrderServiceTest {
    @Autowired
    private OrderRepository orderRepository;
    private OrderService orderService;

    Customer customer = new Customer("Sofia", "sofia@email.com");
    Pizza pizza = new Pizza("Margherita", BigDecimal.valueOf(6.00));
    Order order = new Order(customer, List.of(new OrderItem(pizza)));

    @BeforeEach
    public void setup() {
        // order.setOrderNumber(1);
        // order.setOrderStatus(OrderStatus.received);
        order.setCustomer(customer);
        order.setOrderItems(List.of(new OrderItem(pizza)));
        // order.setPizzas(new HashSet<Pizza>() {{ add(pizza); }});
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
        savedOrder.setOrderStatus(OrderStatus.DELIVERED);
        orderService.updateOrder(savedOrder.getId(), savedOrder);

        Order foundOrder = orderService.getOrder(savedOrder.getId());

        assertThat(foundOrder.getOrderStatus()).isEqualTo(OrderStatus.DELIVERED);
    }

    @Test
    public void shouldDeleteOrder() {
        assertThrows(NotFoundException.class, () -> {
            Order savedOrder = orderService.createOrder(order);

            orderService.deleteOrder(savedOrder.getId());
            Order foundOrder = orderService.getOrder(savedOrder.getId());

            assertThat(foundOrder).isNull();
        });
    }
}