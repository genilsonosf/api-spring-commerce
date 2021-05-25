package com.gsbraga.apicommerce.controllers;

import com.gsbraga.apicommerce.model.Customer;
import com.gsbraga.apicommerce.model.Customer;
import com.gsbraga.apicommerce.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    
    @PostMapping
    public ResponseEntity<Customer> insert(@RequestBody Customer obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Customer> update(@PathVariable Long id, @RequestBody Customer obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
