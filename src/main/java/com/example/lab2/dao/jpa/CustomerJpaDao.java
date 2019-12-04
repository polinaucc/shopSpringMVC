package com.example.lab2.dao.jpa;

import com.example.lab2.dao.DAO;
import com.example.lab2.domain.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CustomerJpaDao implements DAO<Customer> {

    @PersistenceContext
    EntityManager em;

    public List<Customer> findAll() {
        return em.createQuery("select c from Customer c").getResultList();
    }

    public Customer findById(int id){
        TypedQuery<Customer> query = em.createQuery("select c from Customer c where c.id=:id", Customer.class);

        return query.setParameter("id", id).getSingleResult();
    }

    public Customer findByName(String name){
        TypedQuery<Customer> query = em.createQuery("select c from Customer c where c.name=:name", Customer.class);

        List res =  query.setParameter("name", name).getResultList();

        if(res.isEmpty()) {
            return null;
        }
        return (Customer) res.get(0);
    }

    public Customer create(Customer customer){
        em.persist(customer);

        return customer;
    }

    public boolean deleteById(int id) {
        Customer customer = findById(id);

        em.remove(customer);

        return true;
    }

    public boolean updateById(Customer customer, int id) {
        em.merge(customer);

        return true;
    }
}
