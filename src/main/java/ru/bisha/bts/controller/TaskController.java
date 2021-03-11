package ru.bisha.bts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.bisha.bts.model.dto.ProjectDto;
import ru.bisha.bts.model.dto.TaskDto;
import ru.bisha.bts.model.dto.UserDto;
import ru.bisha.bts.model.entity.Project;
import ru.bisha.bts.model.entity.Task;
import ru.bisha.bts.model.entity.User;
import ru.bisha.bts.service.ProjectService;
import ru.bisha.bts.service.TaskService;
import ru.bisha.bts.service.UserService;
import ru.bisha.bts.service.map.DtoMapService;

import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private DtoMapService dtoMapService;

    public static final String TASKS_ATR = "tasksAtr";

    public static final String ALL_TASKS = "all-tasks";

    @GetMapping
    public String showTasks(final Model model) {
        List<Task> tasks = taskService.findAll();
        model.addAttribute(TASKS_ATR, tasks);
        return ALL_TASKS;
    }

    @GetMapping("/from-user")
    public String showTasksFromUser(@RequestParam(value = "userId") final int id, final Model model) {
        User user = userService.findById(id);
        List<Task> tasks = user.getTasks();
        model.addAttribute("fromAtr", "user");
        model.addAttribute("fromId", id);
        model.addAttribute(TASKS_ATR, tasks);
        return ALL_TASKS;
    }

    @GetMapping("/from-project")
    public String showTasksFromProject(@RequestParam(value = "projectId") final int id, final Model model) {
        Project project = projectService.findById(id);
        List<Task> tasks = project.getTasks();
        model.addAttribute("fromAtr", "project");
        model.addAttribute("fromId", id);
        model.addAttribute(TASKS_ATR, tasks);
        return ALL_TASKS;
    }

    @GetMapping("/new")
    public String addNewTask(final Model model) {
        TaskDto newTask = new TaskDto();

        List<ProjectDto> projects = projectService.findAll()
                .stream()
                .map(dtoMapService::mapToDto)
                .collect(Collectors.toList());

        List<UserDto> users = userService.findAll()
                .stream()
                .map(dtoMapService::mapToDto)
                .collect(Collectors.toList());

        model.addAttribute("taskAtr", newTask);
        model.addAttribute("projectsAtr", projects);
        model.addAttribute("usersAtr", users);
        return "task-info";
    }

    @GetMapping("/update")
    public String updateUser(@RequestParam(value = "taskId") final int taskId, final Model model) {
        Task task = taskService.findById(taskId);

        List<ProjectDto> projects = projectService.findAll()
                .stream()
                .map(dtoMapService::mapToDto)
                .collect(Collectors.toList());

        List<UserDto> users = userService.findAll()
                .stream()
                .map(dtoMapService::mapToDto)
                .collect(Collectors.toList());

        TaskDto taskDto = dtoMapService.mapToDto(task);
        model.addAttribute("usersAtr", users);
        model.addAttribute("projectsAtr", projects);
        model.addAttribute("taskAtr", taskDto);
        return "task-info";
    }

    @PostMapping
    public String processNewTask(@ModelAttribute final TaskDto taskDto) {
        Task task = dtoMapService.mapToEntity(taskDto);
        taskService.save(task);
        return "redirect:/tasks";
    }

    @RequestMapping("/delete")
    public String deleteTask(@RequestParam(value = "taskId") final int taskId,
                             @RequestParam(value = "fromAtr", required = false) final String from,
                             @RequestParam(value = "fromId", required = false) final int fromId) {

        taskService.deleteById(taskId);
        if (from.equals("project")) {
            return "redirect:/tasks/from-project/?projectId=" + fromId;
        }
        if (from.equals("user")) {
            return "redirect:/tasks/from-user/?userId=" + fromId;
        }

        return "redirect:/tasks";
    }
}