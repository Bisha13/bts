package ru.bisha.bts.service.map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bisha.bts.model.dto.ProjectDto;
import ru.bisha.bts.model.dto.TaskDto;
import ru.bisha.bts.model.dto.UserDto;
import ru.bisha.bts.model.entity.Project;
import ru.bisha.bts.model.entity.Task;
import ru.bisha.bts.model.entity.User;
import ru.bisha.bts.service.ProjectService;
import ru.bisha.bts.service.UserService;

@Service
public class DtoMapService {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    public UserDto mapToDto(User user) {
        return new UserDto(user.getId(), user.getName());
    }

    public ProjectDto mapToDto(Project project) {
        return new ProjectDto(project.getId(), project.getName());
    }

    public TaskDto mapToDto(Task task) {
        return new TaskDto(
                task.getId(),
                task.getTheme(),
                task.getProject().getId(),
                task.getType(),
                task.getPriority(),
                task.getExecutor().getId(),
                task.getDescription()
                );
    }

    public Task mapToEntity(TaskDto taskDto) {
        return new Task(taskDto.getId(),
                taskDto.getTheme(),
                projectService.findById(taskDto.getProject()),
                taskDto.getType(),
                taskDto.getPriority(),
                userService.findById(taskDto.getExecutor()),
                taskDto.getDescription()
                );
    }
}
