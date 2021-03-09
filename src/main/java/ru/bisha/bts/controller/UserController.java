package ru.bisha.bts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bisha.bts.model.User;
import ru.bisha.bts.service.UserService;

import java.util.Set;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping
    public String showUsers(Model model) {
        Set<User> users = userService.findAll();
        model.addAttribute("usersAtr", users);
        return "all-users";
    }
}
