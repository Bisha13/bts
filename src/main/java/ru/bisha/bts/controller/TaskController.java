package ru.bisha.bts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.bisha.bts.model.entity.Task;
import ru.bisha.bts.model.entity.User;
import ru.bisha.bts.service.TaskService;
import ru.bisha.bts.service.UserService;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    @Autowired
    UserService userService;

    @RequestMapping
    public String showTasks(Model model) {
        Set<Task> tasks = taskService.findAll();
        model.addAttribute("tasksAtr", tasks);
        return "all-tasks";
    }

    @GetMapping("/from-user")
    public String showTasks(@RequestParam(value = "userId", required = false) int id, Model model) {
        User user = userService.findById(id);
        List<Task> tasks = user.getTasks();
        model.addAttribute("tasksAtr", tasks);
        return "all-tasks";
    }
}
