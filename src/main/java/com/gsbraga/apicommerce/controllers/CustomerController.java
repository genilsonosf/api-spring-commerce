package com.gsbraga.apicommerce.controllers;

import com.gsbraga.apicommerce.model.Customer;
import com.gsbraga.apicommerce.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = { "/customers" })
public class CustomerController {

    @Autowired
    CustomerService service;

    @GetMapping
    public ResponseEntity<List<Customer>> findAll() {
        List<Customer> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
}
