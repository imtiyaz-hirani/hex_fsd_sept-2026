package com.helpdesk.repository;

import com.helpdesk.model.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepository {

    private final JdbcTemplate jdbcTemplate;

    public CustomerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertCustomer(Customer customer) {
        String sql = "insert into customer values (?,?,?,?,?)";
        Object[] values = new Object[]{customer.getId(), customer.getName(), customer.getAge(), customer.getPlan().toString(), customer.getUser().getId()};
        jdbcTemplate.update(sql,values);
    }
}
