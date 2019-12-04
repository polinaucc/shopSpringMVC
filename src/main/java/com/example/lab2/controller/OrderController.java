package com.example.lab2.controller;


import com.example.lab2.domain.Ord;
import com.example.lab2.services.OrderService;
import com.example.lab2.services.springData.OrderServiceSD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/orders")
public class OrderController {
//    @Autowired
//    OrderService orderService;

    @Autowired
    OrderServiceSD orderService;

    @GetMapping("/")
    public List<Ord> getAllOrders() {
        return this.orderService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity getOrderById(@PathVariable(name = "id") int id) {
        return this.orderService.findById(id)
                .map(order -> ResponseEntity.ok(order))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<Ord> createOrder(@RequestBody Ord order) {
        Ord createdOrder = this.orderService.createOrder(order);

        return new ResponseEntity<Ord>(createdOrder, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteOder(@PathVariable(name = "id") int id) {
        boolean removed = this.orderService.deleteOrderById(id);

        if (removed) {
            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateOrder(@RequestBody Ord order, @PathVariable(name = "id") int id) {
        boolean updated = this.orderService.updateOrderById(order, id);

        if (updated) {
            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}