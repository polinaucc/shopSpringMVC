package com.example.lab2.dao;

import com.example.lab2.domain.Customer;
import com.example.lab2.domain.Good;
import com.example.lab2.domain.Ord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OrderDao implements DAO<Ord> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static GoodDao goodDao;
    private static CustomerDao customerDao;

    @Autowired
    public void setGoodDao(GoodDao goodDao) {
        OrderDao.goodDao = goodDao;
    }

    @Autowired
    public void setCustomerDao(CustomerDao customerDao) {
        OrderDao.customerDao = customerDao;
    }

    private static Ord mapRow(ResultSet resultSet, int i) throws SQLException {
        int customerId = resultSet.getInt("id_customer");
        int goodId = resultSet.getInt("id_good");
        Customer customer = customerId == 0 ? null : OrderDao.customerDao.findById(customerId);
        Good good = goodId == 0 ? null : OrderDao.goodDao.findById(goodId);

        return Ord.builder()
                .id(resultSet.getInt("id"))
                .id_good(good)
                .id_customer(customer)
                .date(resultSet.getDate("date"))
                .build();
    }

    public List<Ord> findAll() {
        return this.jdbcTemplate.query("select * from ord", OrderDao::mapRow);
    }

    public Ord findById(int id){
        try {
            return this.jdbcTemplate.queryForObject("select * from ord where id=?", new Object[]{id}, OrderDao::mapRow);
        }
        catch(EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public Ord create(Ord ord){
        final String sql = "insert into ord (id_good, id_customer, date) values (?,?,?)";
        int rowCount = this.jdbcTemplate.update(sql, ord.getId_customer().getId(), ord.getId_good().getId(), ord.getDate());

        return rowCount > 0 ? ord : null;
    }

    public boolean deleteById(int id) {
        final String sql = "delete from good where id=?";
        return this.jdbcTemplate.update(sql, id)>0;
    }

    public boolean updateById(Ord ord, int id){
        final String sql ="update ord set id_good=?, id_customer=?, date=?";
        return this.jdbcTemplate.update(sql, ord.getId_customer(), ord.getId_customer(), ord.getDate()) > 0;
    }
}
