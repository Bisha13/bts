package ru.bisha.bts.controller;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.bisha.bts.model.entity.Project;
import ru.bisha.bts.model.entity.Task;
import ru.bisha.bts.model.entity.User;
import ru.bisha.bts.service.ProjectService;
import ru.bisha.bts.service.TaskService;
import ru.bisha.bts.service.UserService;


@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    public static final String TASKS_ATR = "tasksAtr";

    public static final String ALL_TASKS = "all-tasks";

    @GetMapping
    public String showTasks(final Model model) {
        Set<Task> tasks = taskService.findAll();
        model.addAttribute(TASKS_ATR, tasks);
        return ALL_TASKS;
    }

    @GetMapping("/from-user")
    public String showTasksFromUser(@RequestParam(value = "userId")
                                        final int id, final Model model) {
        User user = userService.findById(id);
        List<Task> tasks = user.getTasks();
        model.addAttribute(TASKS_ATR, tasks);
        return ALL_TASKS;
    }

    @GetMapping("/from-project")
    public String showTasksFromProject(@RequestParam(value = "projectId")
                                           final int id, final Model model) {
        Project project = projectService.findById(id);
        List<Task> tasks = project.getTasks();
        model.addAttribute(TASKS_ATR, tasks);
        return ALL_TASKS;
    }
}
