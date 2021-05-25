package com.gsbraga.apicommerce.controllers;

import com.gsbraga.apicommerce.model.Staff;
import com.gsbraga.apicommerce.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
