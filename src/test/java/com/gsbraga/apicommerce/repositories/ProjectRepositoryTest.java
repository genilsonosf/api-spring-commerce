package com.gsbraga.apicommerce.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.gsbraga.apicommerce.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProjectRepositoryTest {

    @Autowired
    private ProductRepository repo;

    @Test
    public void testProductTV() {
        List<Product> products = repo.findByNameContaining("TV");
        assertThat(products.size()).isEqualTo(1);
        assertThat(Objects.equals(products.get(0).getModelYear(), 2020));
    }

    @Test
    public void testProductIdTV() {
        Optional<Product> product = repo.findById((long) 7);
        assertThat(product.get().getName().equals("TV"));
    }

    @Test
    public void testProductMacBookPro() {
        List<Product> products = repo.findByNameContaining("Macbook Pro 2");
        assertThat(products.size()).isEqualTo(0);
    }
}
