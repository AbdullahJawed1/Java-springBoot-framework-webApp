package com.example.User_ManagementSystem.view;

import com.example.User_ManagementSystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserFunctions {
    private Map<Long, User> users = new HashMap<>();
    //using map built-in functions of Map put clear remove


    private JdbcTemplate jdbcTemplate;


    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    @Autowired
    public UserFunctions(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User getById(long id) {
        String sql = "SELECT * FROM users WHERE id = ? AND status=0";
        return jdbcTemplate.queryForObject(sql, new Object[] { id }, (rs, rowNum) ->
                new User(rs.getLong("id"), rs.getString("name"),
                        rs.getString("email"), rs.getString("phone")));
    }

    public List<User> getAll() {
        String sql = "SELECT * FROM users where status=0";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new User(rs.getLong("id"), rs.getString("name"),
                        rs.getString("email"), rs.getString("phone")));
    }

    public void save(User user) {
        String sql = "INSERT INTO users (name, email, phone) VALUES (?,?,?)";
        jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getPhone());
    }


public void update(User user) {
    String sql = "UPDATE users SET name = ?, email = ?, phone = ? WHERE id = ?";
    jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getPhone(), user.getId());
}

//    public void delete(long id) {
//        String sql = "DELETE FROM users WHERE id = ?";
//        jdbcTemplate.update(sql, id);
//    }

    public void delete(User user) {
        String sql = "UPDATE users SET status = 1 WHERE id = ?";
        jdbcTemplate.update(sql,user.getId());
    }


//    public int deleteAll() {
//        String sql = "DELETE FROM users";
//        return jdbcTemplate.update(sql);
//    }
    
    public int deleteAll() {
    String sql = "UPDATE users SET status = 1";
    return jdbcTemplate.update(sql);
}
}

