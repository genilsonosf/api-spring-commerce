package com.gsbraga.apicommerce.services;

import com.gsbraga.apicommerce.model.Customer;
import com.gsbraga.apicommerce.model.Customer;
import com.gsbraga.apicommerce.repositories.CustomerRepository;
import com.gsbraga.apicommerce.services.exceptions.ControllerNotFoundException;
import com.gsbraga.apicommerce.services.exceptions.DatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public List<Customer> findAll() {
        return repository.findAll();
    }

    public Customer findById(long id) {
        return repository.findById(id).get();
    }


    public Customer insert(Customer obj) {
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

    public Customer update(Long id, Customer obj) {
        try {
            Customer entity = repository.getById(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException(id);
        }
    }

    private void updateData(Customer entity, Customer obj) {
        entity.setFirstName(obj.getFirstName());
        entity.setLastName(obj.getLastName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());
        entity.setState(obj.getState());
        entity.setZipCode(obj.getZipCode());
    }
}

