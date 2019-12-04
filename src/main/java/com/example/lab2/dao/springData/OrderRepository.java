package com.example.lab2.dao.springData;

import com.example.lab2.domain.Ord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Ord, Integer> {
}
