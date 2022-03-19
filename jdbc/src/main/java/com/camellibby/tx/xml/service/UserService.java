package com.camellibby.tx.xml.service;

import com.camellibby.tx.xml.User;

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
     * @param users
     */
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
