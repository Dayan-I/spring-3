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

    @RequestMapping(value = "user/edituser", method = RequestMethod.GET)
    public String editUser(Model model, @RequestParam(name = "id") String id) {
        model.addAttribute("user", userService.findById(Long.parseLong(id)));
        return "useredit";
    }

    @RequestMapping(value = "user/edit", method = RequestMethod.PATCH)
    public String editUserBy(@ModelAttribute("user") User user) {
        userService.updateUser(user, user.getId());
        return "redirect:/";
    }
}
