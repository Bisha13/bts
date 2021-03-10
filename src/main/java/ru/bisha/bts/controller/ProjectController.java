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
import ru.bisha.bts.service.ProjectService;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public String showAllProjects(final Model model) {
        Set<Project> projects = projectService.findAll();
        model.addAttribute("projectsAtr", projects);
        return "all-projects";
    }


    @GetMapping("/new")
    public String addNewProject(final Model model) {
        Project newProject = new Project();
        model.addAttribute("projectAtr", newProject);
        return "project-info";
    }

    @PostMapping
    public String processNewProject(@ModelAttribute Project project) {
        System.out.println(project.getName());
        projectService.save(project);
        return "redirect:/projects";
    }
}
