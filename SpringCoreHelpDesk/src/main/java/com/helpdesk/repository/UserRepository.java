package com.helpdesk.repository;

import com.helpdesk.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertUser(User user) {
        String sql = "insert into users values (?,?,?,?,?)";
        Object[] values = new Object[]{user.getId(), user.getUsername(), user.getPassword(), user.getRole().toString(), user.isActive()};
        jdbcTemplate.update(sql,values);
    }
}
// for insertion, updation and deletion ops u use update method