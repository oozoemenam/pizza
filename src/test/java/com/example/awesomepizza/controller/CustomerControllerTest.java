package com.example.awesomepizza.controller;

import com.example.awesomepizza.domain.Customer;
import com.example.awesomepizza.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.zalando.problem.ProblemModule;
import org.zalando.problem.violations.ConstraintViolationProblemModule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;


@WebMvcTest(controllers = CustomerController.class)
@ActiveProfiles("test")
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ModelMapper modelMapper;

    private List<Customer> customerList;

    @BeforeEach
    void setUp() {
        this.customerList = new ArrayList<>();
        this.customerList.add(new Customer(1L, "customer1", "customer1@gmail.com", new HashSet<>()));
        this.customerList.add(new Customer(2L, "customer2", "customer2@gmail.com", new HashSet<>()));
        this.customerList.add(new Customer(3L, "customer3", "customer3@gmail.com", new HashSet<>()));

        objectMapper.registerModule(new ProblemModule());
        objectMapper.registerModule(new ConstraintViolationProblemModule());
    }

    @Test
    void shouldFetchAllCustomers() throws Exception {

        given(customerService.getCustomers()).willReturn(customerList);

        this.mockMvc.perform(get("/api/v1/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(customerList.size())));
    }

    @Test
    void shouldFetchOneCustomerById() throws Exception {
        final Long customerId = 1L;
        final Customer customer = new Customer(1L, "ten", "ten@mail.com", new HashSet<>());

        given(customerService.getCustomer(customerId)).willReturn(customer);

        this.mockMvc.perform(get("/api/v1/customers/{id}", customerId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is(customer.getEmail())))
                .andExpect(jsonPath("$.name", is(customer.getName())));
    }

//        @Test
//        void shouldReturn404WhenFindCustomerById() throws Exception {
//            final Long customerId = 1L;
//            given(customerService.findCustomerById(customerId)).willReturn(Optional.empty());
//
//            this.mockMvc.perform(get("/api/customer/{id}", customerId))
//                    .andExpect(status().isNotFound());
//        }
//
//        @Test
//        void shouldCreateNewCustomer() throws Exception {
//            given(customerService.createCustomer(any(Customer.class))).willAnswer((invocation) -> invocation.getArgument(0));
//
//            Customer customer = new Customer(null, "newcustomer1@gmail.com", "pwd", "Name");
//
//            this.mockMvc.perform(post("/api/customers")
//                            .contentType(MediaType.APPLICATION_JSON_UTF8)
//                            .content(objectMapper.writeValueAsString(customer)))
//                    .andExpect(status().isCreated())
//                    .andExpect(jsonPath("$.email", is(customer.getEmail())))
//                    .andExpect(jsonPath("$.password", is(customer.getPassword())))
//                    .andExpect(jsonPath("$.name", is(customer.getName())))
//            ;
//        }
//
//        @Test
//        void shouldReturn400WhenCreateNewCustomerWithoutEmail() throws Exception {
//            Customer customer = new Customer(null, null, "pwd", "Name");
//
//            this.mockMvc.perform(post("/api/customers")
//                            .contentType(MediaType.APPLICATION_JSON_UTF8)
//                            .content(objectMapper.writeValueAsString(customer)))
//                    .andExpect(status().isBadRequest())
//                    .andExpect(header().string("Content-Type", is("application/problem+json")))
//                    .andExpect(jsonPath("$.type", is("https://zalando.github.io/problem/constraint-violation")))
//                    .andExpect(jsonPath("$.title", is("Constraint Violation")))
//                    .andExpect(jsonPath("$.status", is(400)))
//                    .andExpect(jsonPath("$.violations", hasSize(1)))
//                    .andExpect(jsonPath("$.violations[0].field", is("email")))
//                    .andExpect(jsonPath("$.violations[0].message", is("Email should not be empty")))
//                    .andReturn()
//            ;
//        }
//
//        @Test
//        void shouldUpdateCustomer() throws Exception {
//            Long customerId = 1L;
//            Customer customer = new Customer(customerId, "customer1@gmail.com", "pwd", "Name");
//            given(customerService.findCustomerById(customerId)).willReturn(Optional.of(customer));
//            given(customerService.updateCustomer(any(Customer.class))).willAnswer((invocation) -> invocation.getArgument(0));
//
//            this.mockMvc.perform(put("/api/customers/{id}", customer.getId())
//                            .contentType(MediaType.APPLICATION_JSON_UTF8)
//                            .content(objectMapper.writeValueAsString(customer)))
//                    .andExpect(status().isOk())
//                    .andExpect(jsonPath("$.email", is(customer.getEmail())))
//                    .andExpect(jsonPath("$.password", is(customer.getPassword())))
//                    .andExpect(jsonPath("$.name", is(customer.getName())));
//
//        }
//
//        @Test
//        void shouldReturn404WhenUpdatingNonExistingCustomer() throws Exception {
//            Long customerId = 1L;
//            given(customerService.findCustomerById(customerId)).willReturn(Optional.empty());
//            Customer customer = new Customer(customerId, "customer1@gmail.com", "pwd", "Name");
//
//            this.mockMvc.perform(put("/api/customers/{id}", customerId)
//                            .contentType(MediaType.APPLICATION_JSON_UTF8)
//                            .content(objectMapper.writeValueAsString(customer)))
//                    .andExpect(status().isNotFound());
//
//        }
//
//        @Test
//        void shouldDeleteCustomer() throws Exception {
//            Long customerId = 1L;
//            Customer customer = new Customer(customerId, "customer1@gmail.com", "pwd", "Name");
//            given(customerService.findCustomerById(customerId)).willReturn(Optional.of(customer));
//            doNothing().when(customerService).deleteCustomerById(customer.getId());
//
//            this.mockMvc.perform(delete("/api/customers/{id}", customer.getId()))
//                    .andExpect(status().isOk())
//                    .andExpect(jsonPath("$.email", is(customer.getEmail())))
//                    .andExpect(jsonPath("$.password", is(customer.getPassword())))
//                    .andExpect(jsonPath("$.name", is(customer.getName())));
//
//        }
//
//        @Test
//        void shouldReturn404WhenDeletingNonExistingCustomer() throws Exception {
//            Long customerId = 1L;
//            given(customerService.findCustomerById(customerId)).willReturn(Optional.empty());
//
//            this.mockMvc.perform(delete("/api/customers/{id}", customerId))
//                    .andExpect(status().isNotFound());
//
//        }
}