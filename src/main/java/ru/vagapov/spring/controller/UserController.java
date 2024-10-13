package ru.vagapov.spring.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vagapov.spring.dto.User;
import ru.vagapov.spring.service.UserService;
import java.util.List;

/**
 * Класс-контроллер, отвечает на запросы пользователей, обычных юзеров
 */
@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String user(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("user", userService.findUserByUserName(username));
        return "user";
    }

    @RequestMapping(value = "/search")
    public String findUserByNameAndSurname(Model model, @RequestParam(name = "username") String username) {
        List<User> users = userService.findAll();
        users.removeIf(user -> !user.getUserName().toLowerCase().contains(username.toLowerCase()) && !user.getLastName().toLowerCase().contains(username.toLowerCase()));
        model.addAttribute("users", users);
        if (users.isEmpty()) {
            return "redirect:/";
        } else {
            return "users";
        }
    }
}
