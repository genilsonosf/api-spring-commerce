package com.gsbraga.apicommerce.services;

import com.gsbraga.apicommerce.model.Order;
import com.gsbraga.apicommerce.model.Product;
import com.gsbraga.apicommerce.model.Staff;
import com.gsbraga.apicommerce.model.enums.OrderStatus;
import com.gsbraga.apicommerce.repositories.OrderRepository;
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
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private StaffRepository storeRepository;

    public List<Order> findAll() {
        return repository.findAll();
    }

    public Order findById(Long id) {
        Optional<Order> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ControllerNotFoundException(id));
    }

    public Order insert(Order obj) {
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

    public Order update(Long id, Order obj) {
        try {
            Order entity = repository.getById(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException(id);
        }
    }

    public Order updateStatus(Long id, Order obj) {
        try {
            Order entity = repository.getById(id);

            if(staffRepository.getById(obj.getStaff().getId()).getStore().getId() == entity.getStore().getId()){
                updateData(entity, obj);
            }else{
                throw new DatabaseException("Usuário não possui permissão de alterar status do pedido");
            }
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException(id);
        }
    }

    private void updateData(Order entity, Order obj) {
        entity.setOrderStatus(OrderStatus.valueOf(obj.getOrderStatus()));
    }
}

//R2DBC reativo e não bloqueante 