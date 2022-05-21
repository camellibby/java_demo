package com.camellibby.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luoxinliang
 */
@EnableTransactionManagement
@ComponentScan("com.camellibby.annotation")
public class TransactionApplication {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(TransactionApplication.class);
        UserService userService = ctx.getBean(UserService.class);

        userService.reset();

        try {
            List<User> users = new ArrayList<>(3);
            User user1 = new User();
            user1.setId(0);
            user1.setName("Jeffrey");
            user1.setAge(18);
            users.add(user1);

            User user2 = new User();
            user1.setId(1);
            user2.setName("Libby");
            user2.setAge(18);
            users.add(user2);

            User user3 = new User();
            user3.setId(1);
            user3.setName("Steven");
            user3.setAge(8);
            users.add(user3);

            userService.insertList(users);
        } catch (DuplicateKeyException ex) {
            System.out.println("主键发生冲突，回滚");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        List<User> users = userService.getAll();
        for (User item : users) {
            System.out.printf("id: %s, name: %s, age: %s%n", item.getId(), item.getName(), item.getAge());
        }
    }
}

