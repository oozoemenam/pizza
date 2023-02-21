package com.example.awesomepizza.service;

import com.example.awesomepizza.domain.Customer;
import com.example.awesomepizza.enums.PizzaSizeEnum;
import com.example.awesomepizza.exception.NotFoundException;
import com.example.awesomepizza.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class CustomerServiceTest {
    @Autowired
    private CustomerRepository customerRepository;
    private CustomerService customerService;

    Customer customer = new Customer();

    @BeforeEach
    public void setup() {
        customer.setName("Sofia");
        customer.setEmail("sofia@email.com");

        customerService = new CustomerService(customerRepository);
    }

    @Test
    public void shouldGetCustomers() {
        customerService.createCustomer(customer);

        List<Customer> customerList = customerService.getCustomers();
        Customer savedCustomer = customerList.iterator().next();

        assertThat(savedCustomer).isNotNull();
    }

    @Test
    public void shouldGetCustomer() {
        Customer savedCustomer = customerService.createCustomer(customer);

        Customer foundCustomer = customerService.getCustomer(savedCustomer.getId());

        assertThat(savedCustomer).isEqualTo(foundCustomer);
    }

    @Test
    public void shouldGetCustomersByName() {
        customerService.createCustomer(customer);

        List<Customer> customerList = customerService.getCustomersByName("Margherita");
        Customer savedCustomer = customerList.iterator().next();

        assertThat(savedCustomer).isNotNull();
    }

    @Test
    public void shouldCreateCustomer() {
        customerService.createCustomer(customer);

        List<Customer> customerList = customerService.getCustomers();
        Customer savedCustomer = customerList.iterator().next();

        assertThat(customer).isEqualTo(savedCustomer);
    }

    @Test
    public void shouldUpdateCustomer() {
        Customer savedCustomer = customerService.createCustomer(customer);
        savedCustomer.setEmail("newsofia@email.com");
        customerService.updateCustomer(savedCustomer.getId(), savedCustomer);

        Customer foundCustomer = customerService.getCustomer(savedCustomer.getId());

        assertThat(foundCustomer.getEmail()).isEqualTo("newsofia@email.com");
    }

    @Test
    public void shouldDeleteCustomer() {
        assertThrows(NotFoundException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Customer savedCustomer = customerService.createCustomer(customer);

                customerService.deleteCustomer(savedCustomer.getId());
                Customer foundCustomer = customerService.getCustomer(savedCustomer.getId());

                assertThat(foundCustomer).isNull();
            }
        });
    }
}