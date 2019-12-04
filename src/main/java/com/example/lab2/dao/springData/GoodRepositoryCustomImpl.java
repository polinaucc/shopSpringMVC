package com.example.lab2.dao.springData;

import com.example.lab2.domain.Good;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

public class GoodRepositoryCustomImpl implements GoodRepositoryCustom {
    @PersistenceContext
    EntityManager em;

    @Transactional
    public Good findByName(String name){
        TypedQuery<Good> query = em.createQuery("select g from Good g where g.name=:name", Good.class);

        List res = query.setParameter("name", name).getResultList();

        if(res.isEmpty()) {
            return null;
        }
        return (Good) res.get(0);
    }
}
