package com.camellibby.jdbc.annotation.service;

import com.camellibby.jdbc.annotation.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author luoxinliang
 */
@Service
class UserServiceImpl implements UserService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void reset() {
        jdbcTemplate.execute("DROP TABLE IF EXISTS user_info");
        jdbcTemplate.execute("create table user_info(id LONG PRIMARY KEY AUTO_INCREMENT, name VARCHAR(50), age INT)");
    }

    @Override
    public List<User> getAll() {
        List<User> result = jdbcTemplate.query("SELECT id, name, age FROM user_info", (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setName(rs.getString("name"));
            user.setAge(rs.getInt("age"));
            return user;
        });
        return result;
    }

    @Override
    public void insert(User user) {
        jdbcTemplate.update("INSERT INTO user_info (name, age) VALUES (?, ?)", user.getName(), user.getAge());
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.execute("DELETE FROM user_info");
    }
}
