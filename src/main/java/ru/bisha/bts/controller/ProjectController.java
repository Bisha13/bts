package ru.bisha.bts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bisha.bts.model.Project;
import ru.bisha.bts.service.ProjectService;

import java.util.Set;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @RequestMapping
    public String showAllProjects(Model model) {
        Set<Project> projects = projectService.findAll();
        model.addAttribute("projectsAtr", projects);
        return "all-projects";
    }
}
