package ru.vagapov.spring.controller;

import org.springframework.web.bind.annotation.*;
import ru.vagapov.spring.dto.User;
import ru.vagapov.spring.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable long id) {
        return userService.findById(id);
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        userService.createUser(user);
        return user;
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return user;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/users/books/{id}")
    public List<User> getUsersByBookId(@PathVariable Long id) {
        return userService.findAllUsersByBookId(id);
    }

}
