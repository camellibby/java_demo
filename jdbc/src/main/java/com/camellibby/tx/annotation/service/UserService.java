package com.camellibby.tx.annotation.service;

import com.camellibby.tx.annotation.User;

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

    void insertList(List<User> users);

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
