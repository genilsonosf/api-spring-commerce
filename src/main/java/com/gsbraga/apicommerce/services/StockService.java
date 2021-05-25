package com.gsbraga.apicommerce.services;

import com.gsbraga.apicommerce.model.Stock;
import com.gsbraga.apicommerce.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public List<Stock> findAll() {
        return stockRepository.findAll();
    }

    public Stock findById(long id) {
        return stockRepository.findById(id).get();
    }
}

