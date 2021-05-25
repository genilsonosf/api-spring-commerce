package com.gsbraga.apicommerce.services;

import com.gsbraga.apicommerce.model.Staff;
import com.gsbraga.apicommerce.repositories.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;

    public List<Staff> findAll() {
        return staffRepository.findAll();
    }

    public Staff findById(long id) {
        return staffRepository.findById(id).get();
    }
}

