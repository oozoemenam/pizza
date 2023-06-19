package com.example.awesomepizza.service;

import com.example.awesomepizza.exception.NotFoundException;
import com.example.awesomepizza.model.Customer;
import com.example.awesomepizza.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    private Customer findOrThrow(Long id) {
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

    public Customer getCustomer(Long id) {
        return findOrThrow(id);
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long id, Customer customer) {
        findOrThrow(id);
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        findOrThrow(id);
        customerRepository.deleteById(id);
    }
}
