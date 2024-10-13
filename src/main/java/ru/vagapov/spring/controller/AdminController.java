package ru.vagapov.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vagapov.spring.dto.User;
import ru.vagapov.spring.entity.UserEntity;
import ru.vagapov.spring.service.UserService;

import java.util.List;

/**
 * Класс-контроллер, отвечает на запросы пользователей админов
 */
@Controller
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

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

    @RequestMapping("admin/new")
    public String newUserForm(Model model) {
        UserEntity user = new UserEntity();
        model.addAttribute("user", user);
        return "new_user";
    }

    @RequestMapping(value = "/admin/save", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") User user) {
        userService.createUser(user);
        return "redirect:/";
    }

    @RequestMapping(value = "admin/{id}/delete", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }

    @RequestMapping(value = "admin/edituser", method = RequestMethod.GET)
    public String editUser(Model model, @RequestParam(name = "id") String id) {
        model.addAttribute("user", userService.findById(Long.parseLong(id)));
        return "edit";
    }

    @RequestMapping(value = "admin/edit", method = RequestMethod.PATCH)
    public String editUserBy(@ModelAttribute("user") User user) {
        userService.updateUser(user, user.getId());
        return "redirect:/";
    }

}
