package com.example.lab2.services.springData;

import com.example.lab2.dao.jpa.CustomerJpaDao;
import com.example.lab2.dao.jpa.GoodJpaDao;
import com.example.lab2.dao.jpa.OrderJpaDao;
import com.example.lab2.dao.springData.CustomerRepository;
import com.example.lab2.dao.springData.GoodRepository;
import com.example.lab2.dao.springData.OrderRepository;
import com.example.lab2.domain.Customer;
import com.example.lab2.domain.Good;
import com.example.lab2.domain.Ord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ShopServiceSD {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    GoodRepository goodRepository;
    @Autowired
    OrderRepository orderRepository;


    public String registrate(String name, String login, String password, Date birth) {
        Customer customer = customerRepository.findByName(name);
        if (customer != null) {
            return "Customer exists with such name";
        }
        Customer createdCustomer = customerRepository.save(Customer.builder().name(name).login(login).password(password).birth(birth).build());

        if (createdCustomer != null) {
            return "Customer was registered";
        }

        return "Customer can not be regitered";
    }

    public String makeOrder(String name, String goodName) {
        Customer exCustomer = customerRepository.findByName(name);
        Good exGood = goodRepository.findByName(goodName);
        if (exCustomer != null && exGood != null) {
            Ord order = orderRepository.save(Ord.builder().id_good((Good) exGood).id_customer((Customer) exCustomer).date(new Date()).build());
            if (order != null) return "Order with " + goodName + " was done. Thank you, " + name;
        }
        return "The order can't be done";
    }
}
