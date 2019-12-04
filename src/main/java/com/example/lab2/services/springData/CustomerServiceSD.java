package com.example.lab2.services.springData;

import com.example.lab2.dao.jpa.CustomerJpaDao;
import com.example.lab2.dao.springData.CustomerRepository;
import com.example.lab2.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceSD {

    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> findAll() {
        List<Customer> resultList = new ArrayList<Customer>();
        customerRepository.findAll().forEach(resultList::add);

        return resultList;
    }

    public Optional<Customer> findById(int id) {
        return customerRepository.findById(id);
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public boolean deleteCustomerById(int id) {
        customerRepository.deleteById(id);
        return true;
    }

    public boolean updateCustomerById(Customer customer, int id) {
        customerRepository.save(customer);
        return true;
    }
}
