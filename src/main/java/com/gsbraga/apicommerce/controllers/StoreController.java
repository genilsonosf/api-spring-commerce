package com.gsbraga.apicommerce.controllers;

import com.gsbraga.apicommerce.model.Store;
import com.gsbraga.apicommerce.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = { "/stores" })
public class StoreController {

    @Autowired
    StoreService service;

    @GetMapping
    public ResponseEntity<List<Store>> findAll() {
        List<Store> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
}
