package com.example.lab2.services;

import com.example.lab2.dao.GoodDao;
import com.example.lab2.dao.jpa.GoodJpaDao;
import com.example.lab2.domain.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GoodService {

//    @Autowired
//    private GoodDao goodDao;

    @Autowired
    GoodJpaDao goodDao;

    public List<Good> findAll() {
        return this.goodDao.findAll();
    }

    public Optional<Good> findById(int id) {
        try {
            return Optional.of(this.goodDao.findById(id));
        } catch(Exception ex) {
            return Optional.empty();
        }
    }

    public Good createGood(Good good) {
        return this.goodDao.create(good);
    }

    public boolean deleteGooDById(int id) {
        return this.goodDao.deleteById(id);
    }

    public boolean updateGoodById(Good good, int id) {
        return this.goodDao.updateById(good, id);
    }
}
