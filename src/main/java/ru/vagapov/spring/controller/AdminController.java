package ru.vagapov.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vagapov.spring.dto.Role;
import ru.vagapov.spring.dto.User;
import ru.vagapov.spring.service.UserService;
import ru.vagapov.spring.service.impl.UserRolesService;

import java.util.List;
import java.util.Set;

/**
 * Класс-контроллер, отвечает на запросы пользователей админов
 */
@RequestMapping("/admin")
@Controller
public class AdminController {

    private final UserService userService;
    private final UserRolesService userRolesService;

    public AdminController(UserService userService, UserRolesService userRolesService) {
        this.userService = userService;
        this.userRolesService = userRolesService;
    }

    @GetMapping
    public String home(Model model, @RequestParam(name = "username", required = false) String username) {
        List<User> users;
        if (username != null) {
            users = userService.findAllUsersByPartOfNameOrLastName(username);
        } else {
            users = userService.findAll();
        }
        model.addAttribute("users", users);
        return "admin";
    }

    @RequestMapping("/new")
    public String newUserForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "new_user";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") User user) {
        userService.createUser(user);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/edituser", method = RequestMethod.GET)
    public String editUser(Model model, @RequestParam(name = "id") String id) {
        User user = userService.findById(Long.parseLong(id));
        Set<Role> selectableRoles = userRolesService.findAllRoles();
        model.addAttribute("user", user);
        model.addAttribute("roles", selectableRoles);
        return "edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PATCH)
    public String editUserBy(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }
}
