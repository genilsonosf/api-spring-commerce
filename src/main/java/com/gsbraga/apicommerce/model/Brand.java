package com.gsbraga.apicommerce.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="brands")
public class Brand implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="brand_id")
    private Long id;

    @Column(name ="brand_name")
    private String name;

    @OneToMany(mappedBy="brand")
    private Set<Product> products;

    public Brand() {

    }

    public Brand(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Set<Product> getProducts() {
//        return products;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Brand brand = (Brand) o;
        return id.equals(brand.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}