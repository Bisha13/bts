package ru.bisha.bts.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.bisha.bts.model.dto.UserDto;
import ru.bisha.bts.model.entity.User;
import ru.bisha.bts.service.UserService;
import ru.bisha.bts.service.map.DtoMapService;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private DtoMapService dtoMapService;

    @GetMapping
    public String showUsers(final Model model) {
        List<User> users = userService.findAll();
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
        UserDto userDto = dtoMapService.mapToDto(user);
        model.addAttribute("userAtr", userDto);
        return "user-info";
    }

    @PostMapping
    public String processNewUser(@ModelAttribute final UserDto userDto) {
        User user = userService.findById(userDto.getId());
        if (user == null) {
            user = new User();
        }
        user.setName(userDto.getName());
        userService.save(user);
        return "redirect:/users";
    }
}
