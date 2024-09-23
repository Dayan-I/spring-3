package ru.vagapov.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.vagapov.spring.config.AppConfig;
import ru.vagapov.spring.dto.User;
import ru.vagapov.spring.service.UserService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = ctx.getBean(UserService.class);

        //Здесь можете протестировать создание своих пользователей
        User user1 = new User();
        user1.setLastName("fff");
        user1.setUserName("f=aaa");
        user1.setAge(7);
        user1.setEmail("gfnjfnd");
        user1.setPassword("fff");
        userService.createUser(user1);
        userService.createUser(new User(2L,"bob", "dylan", "gmail", "pipi", 2));
        userService.createUser(new User(3L,"bob", "dylan", "gmail", "pipi", 2));
        userService.createUser(new User(4L,"bob", "dylan", "gmail", "pipi", 2));

        List<User> users = userService.findAll();
        for (User user : users) {
            System.out.println(user);
        }

        ctx.close();
    }
}