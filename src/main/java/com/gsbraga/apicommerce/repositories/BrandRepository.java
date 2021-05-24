package com.gsbraga.apicommerce.repositories;

import com.gsbraga.apicommerce.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    public List<Brand> findByNameContaining(@Param("name") String name);
}