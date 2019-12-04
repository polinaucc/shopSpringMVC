package com.example.lab2.dao.jpa;

import com.example.lab2.dao.DAO;
import com.example.lab2.domain.Ord;
import org.springframework.stereotype.Repository;;import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class OrderJpaDao implements DAO<Ord> {

    @PersistenceContext
    EntityManager em;

    public List<Ord> findAll() {
        return em.createQuery("select o from Ord o").getResultList();
    }

    public Ord findById(int id){
        TypedQuery<Ord> query = em.createQuery("select o from Ord o where o.id=:id", Ord.class);

        return query.setParameter("id", id).getSingleResult();
    }

    public Ord create(Ord ord){
        em.persist(ord);

        return ord;
    }

    public boolean deleteById(int id) {
        Ord o = findById(id);
        em.remove(o);
        return true;
    }

    public boolean updateById(Ord ord, int id){
        em.merge(ord);
        return true;
    }
}
