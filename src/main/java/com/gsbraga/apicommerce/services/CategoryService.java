package com.gsbraga.apicommerce.services;

import com.gsbraga.apicommerce.model.Category;
import com.gsbraga.apicommerce.repositories.CategoryRepository;
import com.gsbraga.apicommerce.services.exceptions.ControllerNotFoundException;
import com.gsbraga.apicommerce.services.exceptions.DatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<Category> findAll() {
        return repository.findAll();
    }

    public Category findById(Long id) {
        Optional<Category> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ControllerNotFoundException(id));
    }

    public Category insert(Category obj) {
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

    public Category update(Long id, Category obj) {
        try {
            Category entity = repository.getById(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException(id);
        }
    }

    private void updateData(Category entity, Category obj) {
        entity.setName(obj.getName());
    }
}

