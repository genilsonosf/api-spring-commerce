package com.gsbraga.apicommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name="products")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="product_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="category_id", referencedColumnName="category_id", nullable=false)
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="brand_id", referencedColumnName="brand_id", nullable=false)
    private Brand brand;

    @OneToMany(mappedBy = "id.product")
    private Set<OrderItem> items = new HashSet<>();

    @OneToMany(
            mappedBy="product",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<Stock> stocks = new ArrayList<>();

    @Column(name ="product_name")
    private String name;

    @Column(name ="model_year")
    private int modelYear;

    @Column(name ="list_price")
    private Double price;

    public Product() {

    }

    public Product(Long id, Category category, Brand brand, String name, Double price, int modelYear) {
        super();
        this.id = id;
        this.category = category;
        this.brand = brand;
        this.name = name;
        this.price = price;
        this.modelYear = modelYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }
}