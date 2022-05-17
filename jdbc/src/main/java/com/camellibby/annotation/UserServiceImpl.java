package com.camellibby.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        SimpleDriverDataSource dataSource = (SimpleDriverDataSource) jdbcTemplate.getDataSource();
        if (dataSource.getDriver() instanceof com.mysql.cj.jdbc.Driver) {
            jdbcTemplate.execute("DROP TABLE IF EXISTS user_info");
            jdbcTemplate.execute("create table user_info(id bigint NOT NULL AUTO_INCREMENT, name varchar(50), age int, PRIMARY KEY (`id`))");
        } else if (dataSource.getDriver() instanceof org.h2.Driver) {
            jdbcTemplate.execute("DROP TABLE IF EXISTS user_info");
            jdbcTemplate.execute("create table user_info(id LONG PRIMARY KEY AUTO_INCREMENT, name VARCHAR(50), age INT)");
        }
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

    @Transactional
    @Override
    public void insertList(List<User> users) {
        for (User user : users) {
            jdbcTemplate.update("INSERT INTO user_info (id, name, age) VALUES (?, ?, ?)", user.getId(), user.getName(), user.getAge());
        }
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.execute("DELETE FROM user_info");
    }
}
