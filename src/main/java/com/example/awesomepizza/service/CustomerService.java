package com.example.awesomepizza.service;

import com.example.awesomepizza.exception.NotFoundException;
import com.example.awesomepizza.domain.Customer;
import com.example.awesomepizza.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    private Customer findOrThrow(long id) {
        return customerRepository.findById(id).orElseThrow(
                () -> new NotFoundException(
                        "Customer with id " + id + " was not found")
        );
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public List<Customer> getCustomersByName(String name) {
        return customerRepository.findByNameContaining(name);
    }

    public Customer getCustomer(long id) {
        return findOrThrow(id);
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(long id, Customer customer) {
        findOrThrow(id);
        return customerRepository.save(customer);
    }

    public void deleteCustomer(long id) {
        findOrThrow(id);
        customerRepository.deleteById(id);
    }
}
