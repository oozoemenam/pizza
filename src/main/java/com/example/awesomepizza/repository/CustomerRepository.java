package com.example.awesomepizza.repository;

import com.example.awesomepizza.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByEmail(String email);

    List<Customer> findByNameContaining(String name);
}
