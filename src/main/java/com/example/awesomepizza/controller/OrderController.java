package com.example.awesomepizza.controller;

import com.example.awesomepizza.dto.OrderDto;
import com.example.awesomepizza.model.Order;
import com.example.awesomepizza.service.OrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<OrderDto>> getOrders() {
        List<Order> orders = orderService.getOrders();
        List<OrderDto> orderDtos = orders.stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(orderDtos, HttpStatus.OK);
    }

    @GetMapping("/pending")
    public ResponseEntity<List<OrderDto>> getOrdersPending() {
        List<Order> orders = orderService.getOrdersPending();
        List<OrderDto> orderPendingDtos = orders.stream().map((order) -> mapper.map(order, OrderDto.class)).collect(Collectors.toList());
        return new ResponseEntity<>(orderPendingDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable("id") Long id) {
        OrderDto orderDto = convertToDto(orderService.getOrder(id));
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }

    @GetMapping("/{orderNumber}")
    public ResponseEntity<OrderDto> getOrderByOrderNumber(@PathVariable("orderNumber") Integer orderNumber) {
        OrderDto orderDto = convertToDto(orderService.getOrderByOrderNumber(orderNumber));
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody @Valid OrderDto orderDto) {
        Order order = orderService.createOrder(convertToEntity(orderDto));
        return new ResponseEntity<>(convertToDto(order), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateOrder(
            @PathVariable("id") Long id,
            @Valid @RequestBody OrderDto orderDto
    ) {
        if (!id.equals(orderDto.getId())) throw new
                ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                "id does not match."
        );

        orderService.updateOrder(id, convertToEntity(orderDto));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("id") Long id) {
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private OrderDto convertToDto(Order order) {
        return mapper.map(order, OrderDto.class);
    }

    private Order convertToEntity(@Valid OrderDto orderDto) {
        return mapper.map(orderDto, Order.class);
    }
}
