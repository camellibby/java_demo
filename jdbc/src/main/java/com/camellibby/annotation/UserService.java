package com.camellibby.annotation;

import com.camellibby.annotation.User;
import org.springframework.transaction.annotation.Transactional;

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
