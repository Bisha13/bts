package ru.bisha.bts.controller;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.bisha.bts.model.dto.UserDto;
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
        UserDto newUser = new UserDto();
        model.addAttribute("userAtr", newUser);
        return "user-info";
    }

    @GetMapping("/update")
    public String updateUser(@RequestParam(value = "userId") final int userId, final Model model) {
        User user = userService.findById(userId);
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        model.addAttribute("userAtr", userDto);
        return "user-info";
    }

    @PostMapping
    public String processNewUser(@ModelAttribute UserDto userDto) {
        User user = userService.findById(userDto.getId());
        if (user == null) {
            user = new User();
        }
        user.setName(userDto.getName());
        userService.save(user);
        return "redirect:/users";
    }
}
