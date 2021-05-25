package com.gsbraga.apicommerce.services;

import com.gsbraga.apicommerce.model.Staff;
import com.gsbraga.apicommerce.model.Staff;
import com.gsbraga.apicommerce.repositories.StaffRepository;
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
public class StaffService {

    @Autowired
    private StaffRepository repository;

    public List<Staff> findAll() {
        return repository.findAll();
    }

    public Staff findById(long id) {
        return repository.findById(id).get();
    }

    public Staff findById(Long id) {
        Optional<Staff> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ControllerNotFoundException(id));
    }

    public Staff insert(Staff obj) {
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
    public Staff update(Long id, Staff obj) {
        try {
            Staff entity = repository.getById(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException(id);
        }
    }

    private void updateData(Staff entity, Staff obj) {
        entity.setFirstName(obj.getFirstName());
        entity.setLastName(obj.getLastName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());
        entity.setActive(obj.getActive());
    }
}

