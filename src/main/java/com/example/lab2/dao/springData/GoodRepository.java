package com.example.lab2.dao.springData;

import com.example.lab2.domain.Good;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodRepository extends CrudRepository<Good, Integer>, GoodRepositoryCustom {
}
