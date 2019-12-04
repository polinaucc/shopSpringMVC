package com.example.lab2.dao.springData;

import com.example.lab2.domain.Customer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

public class CustomerRepositoryCustomerImpl implements CustomerRepositoryCustom {
    @PersistenceContext
    EntityManager em;

    @Transactional
    public Customer findByName(String name){
        TypedQuery<Customer> query = em.createQuery("select c from Customer c where c.name=:name", Customer.class);

        List res =  query.setParameter("name", name).getResultList();

        if(res.isEmpty()) {
            return null;
        }
        return (Customer) res.get(0);
    }
}
