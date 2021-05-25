package com.gsbraga.apicommerce.repositories;

import com.gsbraga.apicommerce.model.Product;
import com.gsbraga.apicommerce.model.Stock;
import com.gsbraga.apicommerce.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    public List<Stock> findByStoreIdAndProductId(@Param("product") Product product, @Param("store") Store store);

}