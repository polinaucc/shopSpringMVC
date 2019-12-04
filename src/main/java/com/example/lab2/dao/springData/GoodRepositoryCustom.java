package com.example.lab2.dao.springData;

import com.example.lab2.domain.Good;

public interface GoodRepositoryCustom {
    Good findByName(String name);
}
