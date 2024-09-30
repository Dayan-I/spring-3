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
//        userService.createUser(new User(2L,"bob", "dylan", "gmail", "pipi", 2));
//        userService.createUser(new User(2L,"bib", "dylan", "gmail", "pipi", 2));
//        userService.createUser(new User(3L,"bub", "dylan", "gmail", "pipi", 2));
//        userService.createUser(new User(4L,"bab", "dylan", "gmail", "pipi", 2));

        List<User> users = userService.findAll();
        for (User user : users) {
            System.out.println(user);
        }
        ctx.close();

    }
}