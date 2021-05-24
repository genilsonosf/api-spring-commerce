package com.gsbraga.apicommerce.config;

import com.gsbraga.apicommerce.model.Brand;
import com.gsbraga.apicommerce.model.Category;
import com.gsbraga.apicommerce.model.Product;
import com.gsbraga.apicommerce.repositories.BrandRepository;
import com.gsbraga.apicommerce.repositories.CategoryRepository;
import com.gsbraga.apicommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public void run(String... args) throws Exception {

        Category cat1 = new Category(null, "Electronics");
        Category cat2 = new Category(null, "Books");
        Category cat3 = new Category(null, "Computers");

        Brand brand1 = new Brand(null, "AAA");
        Brand brand2 = new Brand(null, "BBB");
        Brand brand3 = new Brand(null, "CCC");

        Product p1 = new Product(null, cat1, brand3, "TV.", 90.5, 2020);
        Product p2 = new Product(null, cat2, brand2, "Notebook DELL.",  2190.0, 2018);
        Product p3 = new Product(null, cat3, brand1, "Macbook Pro",1250.0, 2017);
        Product p4 = new Product(null, cat1, brand2, "PC Gamer", 1200.0, 2021);
        Product p5 = new Product(null, cat2, brand1, "Fone", 100.99, 2021);

        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
        brandRepository.saveAll(Arrays.asList(brand1, brand2, brand3));
        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

    }
}
