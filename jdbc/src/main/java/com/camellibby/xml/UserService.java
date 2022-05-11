package com.camellibby.xml;

import com.camellibby.xml.User;

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
     * @param users
     */
    void insertList(List<User> users);

    /**
     *
     */
    void deleteAll();
}
