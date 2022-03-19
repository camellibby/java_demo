package com.camellibby.jdbc.annotation;

import com.camellibby.jdbc.annotation.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

/**
 * @author luoxinliang
 */
@ComponentScan("com.camellibby.jdbc.annotation")
public class AnnotationApplication {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AnnotationApplication.class);
        UserService userService = ctx.getBean(UserService.class);

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

