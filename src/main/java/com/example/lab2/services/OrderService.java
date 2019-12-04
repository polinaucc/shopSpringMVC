package com.example.lab2.services;

import com.example.lab2.dao.OrderDao;
import com.example.lab2.dao.jpa.OrderJpaDao;
import com.example.lab2.domain.Ord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

//    @Autowired
//    private OrderDao orderDao;

    @Autowired
    OrderJpaDao orderDao;

    public List<Ord> findAll() {
        return this.orderDao.findAll();
    }

    public Optional<Ord> findById(int id) {
        try {
            return Optional.of(this.orderDao.findById(id));
        } catch(Exception ex) {
            return Optional.empty();
        }
    }

    public Ord createOrder(Ord order) {
        return this.orderDao.create(order);
    }

    public boolean deleteOrderById(int id) {
        return this.orderDao.deleteById(id);
    }

    public boolean updateOrderById(Ord order, int id) {
        return this.orderDao.updateById(order, id);
    }
}
