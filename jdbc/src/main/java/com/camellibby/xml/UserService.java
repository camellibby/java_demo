package com.camellibby.xml;

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
