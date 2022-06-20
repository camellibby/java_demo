package com.camellibby.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author luoxinliang
 */
public class DatabaseApplication {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-jdbc.xml");
        UserService userService = (UserService) ctx.getBean("userService");

        userService.reset();

        User user1 = new User();
        user1.setName("Jeffrey");
        user1.setAge(18);
        userService.insert(user1);

        User user2 = new User();
        user2.setName("Libby");
        user2.setAge(18);
        userService.insert(user2);

        List<User> users = userService.getAll();
        for (User item : users) {
            System.out.printf("id: %s, name: %s, age: %s%n", item.getId(), item.getName(), item.getAge());
        }
    }
}

