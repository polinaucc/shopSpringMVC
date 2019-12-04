package com.example.lab2.dao;

import com.example.lab2.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CustomerDao implements DAO<Customer> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static Customer mapRow(ResultSet resultSet, int i) throws SQLException{
        return Customer.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .login(resultSet.getString("login"))
                .password(resultSet.getString("password"))
                .birth(resultSet.getDate("birth"))
                .build();
    }

    public List<Customer> findAll() {
        return this.jdbcTemplate.query("select * from customer", CustomerDao::mapRow);
    }

    public Customer findById(int id){
        try {
            return this.jdbcTemplate.queryForObject("select * from customer where id=?", new Object[]{id}, CustomerDao::mapRow);
        } catch(EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public Customer findByName(String name){
        try {
            return this.jdbcTemplate.queryForObject(
                    "select * from customer where name=?",
                    new Object[]{name},
                    CustomerDao::mapRow);
        } catch(EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public Customer create(Customer customer){
        final String sql = "insert into customer (name, login, password, birth) values (?,?,?,?)";
        int row = this.jdbcTemplate.update(sql, customer.getName(), customer.getLogin(), customer.getPassword(), customer.getBirth());

        return row > 0 ? customer : null;
    }

    public boolean deleteById(int id) {
        final String sql = "delete from customer where id=?";

        return this.jdbcTemplate.update(sql, id) > 0;
    }

    public boolean updateById(Customer customer, int id){
        final String sql ="update customer set name=?, login=?, password=?, birth=?";

        return this.jdbcTemplate.update(
                sql,
                customer.getName(),
                customer.getLogin(),
                customer.getPassword(),
                customer.getBirth()
        ) > 0;
    }
}
