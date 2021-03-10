package ru.bisha.bts.controller;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bisha.bts.model.entity.Project;
import ru.bisha.bts.model.entity.User;
import ru.bisha.bts.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String showUsers(final Model model) {
        Set<User> users = userService.findAll();
        model.addAttribute("usersAtr", users);
        return "all-users";
    }

    @GetMapping("/new")
    public String addNewUser(final Model model) {
        User newUser = new User();
        model.addAttribute("userAtr", newUser);
        return "user-info";
    }

    @PostMapping
    public String processNewUser(@ModelAttribute User user) {
        System.out.println(user.getName());
        userService.save(user);
        return "redirect:/users";
    }
}
