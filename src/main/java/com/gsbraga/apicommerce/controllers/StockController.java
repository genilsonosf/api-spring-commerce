package com.gsbraga.apicommerce.controllers;

import com.gsbraga.apicommerce.model.Stock;
import com.gsbraga.apicommerce.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = { "/stocks" })
public class StockController {

    @Autowired
    StockService service;

    @GetMapping
    public ResponseEntity<List<Stock>> findAll() {
        List<Stock> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
}
