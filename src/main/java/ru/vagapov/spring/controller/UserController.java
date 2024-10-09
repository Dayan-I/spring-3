package ru.vagapov.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vagapov.spring.dto.User;
import ru.vagapov.spring.entity.UserEntity;
import ru.vagapov.spring.service.UserService;
import java.util.List;

/**
 * Класс-контроллер, отвечает на запросы пользователей
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home(Model model, @RequestParam(name = "username", required = false) String username) {
        if (username != null) {
            List<User> users = userService.findAllUsersByPartOfNameOrLastName(username);
            model.addAttribute("users", users);
            return "index";
        } else {
            List<User> users = userService.findAll();
            model.addAttribute("users", users);
            return "index";
        }
    }
    @RequestMapping("/new")
    public String newUserForm(Model model) {
        UserEntity user = new UserEntity();
        model.addAttribute("user", user);
        return "new_user";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") User user) {
        userService.createUser(user);
        return "redirect:/";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }

    @RequestMapping(value = "/{id}/edituser", method = RequestMethod.GET)
    public String editUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.findById(id));
        return "edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PATCH)
    public String editUserBy(@ModelAttribute("user") User user, Long id) {
        userService.updateUser(user, id);
        return "redirect:/";
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
