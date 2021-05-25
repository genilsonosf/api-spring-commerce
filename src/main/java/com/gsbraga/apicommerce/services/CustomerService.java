package com.gsbraga.apicommerce.services;

import com.gsbraga.apicommerce.model.Customer;
import com.gsbraga.apicommerce.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer findById(long id) {
        return customerRepository.findById(id).get();
    }
}

