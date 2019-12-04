package com.example.lab2.controller;

import com.example.lab2.domain.Customer;
import com.example.lab2.services.CustomerService;
import com.example.lab2.services.springData.CustomerServiceSD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

//    @Autowired
//    CustomerService customerService;

    @Autowired
    CustomerServiceSD customerService;

    @GetMapping("/")
    public List<Customer> getAllCustomers() {
        return this.customerService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity getCustomerById(@PathVariable(name="id") int id) {
        return this.customerService.findById(id)
                .map(customer -> ResponseEntity.ok(customer))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer createdCustomer = this.customerService.createCustomer(customer);

        return new ResponseEntity<Customer>(createdCustomer, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCustomer(@PathVariable(name="id") int id) {
        boolean removed = this.customerService.deleteCustomerById(id);

        if(removed) {
            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCustomer(@RequestBody Customer customer, @PathVariable(name="id") int id) {
        boolean updated = this.customerService.updateCustomerById(customer,id);

        if(updated) {
            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
