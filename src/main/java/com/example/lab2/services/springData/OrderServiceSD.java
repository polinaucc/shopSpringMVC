package com.example.lab2.services.springData;

import com.example.lab2.dao.springData.OrderRepository;
import com.example.lab2.domain.Ord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceSD {
    @Autowired
    OrderRepository orderRepository;

    public List<Ord> findAll() {
        List<Ord> resultList = new ArrayList<Ord>();
        orderRepository.findAll().forEach(resultList::add);

        return resultList;
    }

    public Optional<Ord> findById(int id) {
        return orderRepository.findById(id);
    }

    public Ord createOrder(Ord order) {
        return orderRepository.save(order);
    }

    public boolean deleteOrderById(int id) {
        orderRepository.deleteById(id);
        return true;
    }

    public boolean updateOrderById(Ord order, int id) {
        orderRepository.save(order);

        return true;
    }
}
