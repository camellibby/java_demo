package com.camellibby.jdbc.xml.service;

import com.camellibby.jdbc.xml.User;

import java.util.List;

/**
 * @author luoxinliang
 */
public interface UserService {
    /**
     *
     * @return
     */
    void reset();

    /**
     *
     * @return
     */
    List<User> getAll();

    /**
     *
     * @param user
     */
    void insert(User user);

    /**
     *
     */
    void deleteAll();
}
