package com.example.lab2.dao.springData;

import com.example.lab2.domain.Customer;

public interface CustomerRepositoryCustom {
    Customer findByName(String name);
}
