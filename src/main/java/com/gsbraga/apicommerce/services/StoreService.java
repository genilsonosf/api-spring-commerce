package com.gsbraga.apicommerce.services;

import com.gsbraga.apicommerce.model.Store;
import com.gsbraga.apicommerce.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    public List<Store> findAll() {
        return storeRepository.findAll();
    }

    public Store findById(long id) {
        return storeRepository.findById(id).get();
    }
}

