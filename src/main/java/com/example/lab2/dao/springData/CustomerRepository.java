package com.example.lab2.dao.springData;

import com.example.lab2.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer>, CustomerRepositoryCustom {
}
