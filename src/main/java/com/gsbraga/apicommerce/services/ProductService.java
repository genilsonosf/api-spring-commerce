package com.gsbraga.apicommerce.services;

import com.gsbraga.apicommerce.model.Product;
import com.gsbraga.apicommerce.model.Product;
import com.gsbraga.apicommerce.repositories.ProductRepository;
import com.gsbraga.apicommerce.services.exceptions.ControllerNotFoundException;
import com.gsbraga.apicommerce.services.exceptions.DatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findById(long id) {
        return repository.findById(id).get();
    }
    
    public Product insert(Product obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ControllerNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Product update(Long id, Product obj) {
        try {
            Product entity = repository.getById(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException(id);
        }
    }

    private void updateData(Product entity, Product obj) {
        entity.setName(obj.getName());
    }
}

