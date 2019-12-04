package com.example.lab2.dao;

import com.example.lab2.domain.Customer;
import com.example.lab2.domain.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class GoodDao implements DAO<Good> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static Good mapRow(ResultSet resultSet, int i) throws SQLException{
        return Good.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .price(resultSet.getDouble("price"))
                .category(resultSet.getString("category"))
                .fabricator(resultSet.getString("fabricator"))
                .build();
    }

    public List<Good> findAll() {
        return this.jdbcTemplate.query("select * from good", GoodDao::mapRow);
    }

    public Good findById(int id){
        try {
            return this.jdbcTemplate.queryForObject("select * from good where id=?", new Object[]{id}, GoodDao::mapRow);
        }
        catch(EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public Good findByName(String name){
        try {
            return this.jdbcTemplate.queryForObject("select * from good where name=?", new Object[]{name}, GoodDao::mapRow);
        }
        catch(EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public Good create(Good good){
        final String sql = "insert into good (name, price, category, fabricator) values (?,?,?,?)";
        int row = this.jdbcTemplate.update(sql, good.getName(), good.getPrice(), good.getCategory(), good.getFabricator());
        return row>0?good:null;
    }

    public boolean deleteById(int id) {
        final String sql = "delete from good where id=?";
        return this.jdbcTemplate.update(sql, id)>0;
    }

    public boolean updateById(Good good, int id){
        final String sql ="update customer set name=?, login=?, password=?, birth=?";
        return this.jdbcTemplate.update(sql, good.getName(), good.getPrice(), good.getCategory(), good.getFabricator()) > 0;
    }
}
