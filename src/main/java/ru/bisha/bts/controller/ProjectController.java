package ru.bisha.bts.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.bisha.bts.model.dto.ProjectDto;
import ru.bisha.bts.model.entity.Project;
import ru.bisha.bts.service.ProjectService;
import ru.bisha.bts.service.map.DtoMapService;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private DtoMapService dtoMapService;

    @GetMapping
    public String showAllProjects(final Model model) {
        List<Project> projects = projectService.findAll();
        model.addAttribute("projectsAtr", projects);
        return "all-projects";
    }


    @GetMapping("/new")
    public String addNewProject(final Model model) {
        Project newProject = new Project();
        model.addAttribute("projectAtr", newProject);
        return "project-info";
    }

    @GetMapping("/update")
    public String updateUser(@RequestParam(value = "projectId") final int projectId, final Model model) {
        Project project = projectService.findById(projectId);
        ProjectDto projectDto = dtoMapService.mapToDto(project);
        model.addAttribute("projectAtr", projectDto);
        return "project-info";
    }


    @PostMapping
    public String processNewProject(@ModelAttribute final ProjectDto projectDto) {
        Project project = projectService.findById(projectDto.getId());
        if (project == null) {
            project = new Project();
        }

        project.setName(projectDto.getName());
        projectService.save(project);
        return "redirect:/projects";
    }
}
