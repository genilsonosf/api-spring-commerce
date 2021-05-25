package com.gsbraga.apicommerce.services;

import com.gsbraga.apicommerce.model.Store;
import com.gsbraga.apicommerce.model.Store;
import com.gsbraga.apicommerce.repositories.StoreRepository;
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
public class StoreService {

    @Autowired
    private StoreRepository repository;

    public List<Store> findAll() {
        return repository.findAll();
    }

    public Store findById(long id) {
        return repository.findById(id).get();
    }
    
    public Store findById(Long id) {
        Optional<Store> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ControllerNotFoundException(id));
    }

    public Store insert(Store obj) {
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

    public Store update(Long id, Store obj) {
        try {
            Store entity = repository.getById(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException(id);
        }
    }

    private void updateData(Store entity, Store obj) {
        entity.setName(obj.getName());
        entity.setPhone(obj.getPhone());
        entity.setEmail(obj.getEmail());
        entity.setStreet(obj.getStreet());
        entity.setCity(obj.getCity());
        entity.setState(obj.getState());
        entity.setZipCode(obj.getZipCode());
    }
}

