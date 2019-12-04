package com.example.lab2.services;

import com.example.lab2.dao.CustomerDao;
import com.example.lab2.dao.GoodDao;
import com.example.lab2.dao.OrderDao;
import com.example.lab2.dao.jpa.CustomerJpaDao;
import com.example.lab2.dao.jpa.GoodJpaDao;
import com.example.lab2.dao.jpa.OrderJpaDao;
import com.example.lab2.domain.Customer;
import com.example.lab2.domain.Good;
import com.example.lab2.domain.Ord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ShopService {

//    @Autowired
//    CustomerDao customerDao;
//    @Autowired
//    GoodDao goodDao;
//    @Autowired
//    OrderDao orderDao;

    @Autowired
    CustomerJpaDao customerDao;
    @Autowired
    GoodJpaDao goodDao;
    @Autowired
    OrderJpaDao orderDao;


    public String registrate(String name, String login, String password, Date birth) {
        Customer customer = this.customerDao.findByName(name);
        if (customer != null) {
            return "Customer exists with such name";
        }
        Customer createdCustomer = this.customerDao.create(Customer.builder().name(name).login(login).password(password).birth(birth).build());

        if (createdCustomer != null) {
            return "Customer was registered";
        }

        return "Customer can not be regitered";
    }

    public String makeOrder(String name, String goodName) {
        Customer exCustomer = customerDao.findByName(name);
        Good exGood = goodDao.findByName(goodName);
        if (exCustomer != null && exGood != null) {
            Ord order = orderDao.create(Ord.builder().id_good((Good) exGood).id_customer((Customer) exCustomer).date(new Date()).build());
            if (order != null) return "Order with " + goodName + " was done. Thank you, " + name;
        }
        return "The order can't be done";
    }
}
