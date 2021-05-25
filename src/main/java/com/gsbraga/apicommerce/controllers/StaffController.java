package com.gsbraga.apicommerce.controllers;

import com.gsbraga.apicommerce.model.Staff;
import com.gsbraga.apicommerce.model.Product;
import com.gsbraga.apicommerce.model.Staff;
import com.gsbraga.apicommerce.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping(value = { "/staffs" })
public class StaffController {

    @Autowired
    StaffService service;

    @GetMapping
    public ResponseEntity<List<Staff>> findAll() {
        List<Staff> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Staff> findById(@PathVariable Long id) {
        Staff obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
    
    @PostMapping
    public ResponseEntity<Staff> insert(@RequestBody Staff obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Staff> update(@PathVariable Long id, @RequestBody Staff obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
