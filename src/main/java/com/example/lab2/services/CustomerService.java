package com.example.lab2.services;

import com.example.lab2.dao.CustomerDao;
import com.example.lab2.dao.jpa.CustomerJpaDao;
import com.example.lab2.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerJpaDao customerDao;
//    @Autowired
//    private CustomerDao customerDao;

    public List<Customer> findAll() {
        return this.customerDao.findAll();
    }

    public Optional<Customer> findById(int id) {
        try {
            return Optional.of(this.customerDao.findById(id));
        } catch(Exception ex) {
            return Optional.empty();
        }
    }

    public Customer createCustomer(Customer customer) {
        return this.customerDao.create(customer);
    }

    public boolean deleteCustomerById(int id) {
        return this.customerDao.deleteById(id);
    }

    public boolean updateCustomerById(Customer customer, int id) {
        return this.customerDao.updateById(customer, id);
    }
}
