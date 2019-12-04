package com.example.lab2.dao.jpa;

import com.example.lab2.dao.DAO;
import com.example.lab2.domain.Good;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class GoodJpaDao implements DAO<Good> {

    @PersistenceContext
    EntityManager em;

    public List<Good> findAll() {
        return em.createQuery("select g from Good g").getResultList();
    }

    public Good findById(int id){
        TypedQuery<Good> query = em.createQuery("select g from Good g where g.id=:id", Good.class);

        return query.setParameter("id", id).getSingleResult();
    }

    public Good findByName(String name){
        TypedQuery<Good> query = em.createQuery("select g from Good g where c.name=:name", Good.class);

        return query.setParameter("name", name).getSingleResult();
    }

    public Good create(Good good){
        em.persist(good);

        return good;
    }

    public boolean deleteById(int id) {
        Good customer = findById(id);

        em.remove(customer);

        return true;
    }

    public boolean updateById(Good good, int id) {
        em.merge(good);

        return true;
    }
}
