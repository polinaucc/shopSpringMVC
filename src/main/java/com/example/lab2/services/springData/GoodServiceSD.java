package com.example.lab2.services.springData;

import com.example.lab2.dao.springData.GoodRepository;
import com.example.lab2.domain.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GoodServiceSD {
    @Autowired
    GoodRepository goodRepository;

    public List<Good> findAll() {
        List<Good> resultList = new ArrayList<Good>();
        goodRepository.findAll().forEach(resultList::add);

        return resultList;
    }

    public Optional<Good> findById(int id) {
        return goodRepository.findById(id);
    }

    public Good createGood(Good good) {
        return goodRepository.save(good);
    }

    public boolean deleteGooDById(int id) {
        goodRepository.deleteById(id);
        return true;
    }

    public boolean updateGoodById(Good good, int id) {
        goodRepository.
        goodRepository.save(good);

        return true;
    }

}
