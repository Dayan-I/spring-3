package ru.vagapov.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    public String home(Model model) {
        userService.createUser(null);
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "index";
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

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String deleteUser(@ModelAttribute("user") Long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editUser(@ModelAttribute("user") Long id) {
        return "redirect:/{id}";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String editUserBy(@ModelAttribute("user") User user, Long id) {
        userService.updateUser(user, id);
        return "redirect:/";
    }
    //добавьте url на удаление = /delete который принимает id пользака, после перебрасывает на основную страницу
    //добавьте url на редактирование = /edit который принимает id пользака и перебрасывает на страницу с редактированием (новая страница)
    //добавьте url на постредактирование = /{id} который принимает id пользака в пути и ДТО отредактированого пользователя, после у нас в программе происходит update и перебрасывает на основную страницу
}
