package ru.bisha.bts.service.map;

import org.springframework.stereotype.Service;
import ru.bisha.bts.model.dto.ProjectDto;
import ru.bisha.bts.model.dto.TaskDto;
import ru.bisha.bts.model.dto.UserDto;
import ru.bisha.bts.model.entity.Project;
import ru.bisha.bts.model.entity.Task;
import ru.bisha.bts.model.entity.User;

@Service
public class DtoMapService {

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
                task.getProject().getName(),
                TaskDto.Type.valueOf(task.getType().name()),
                TaskDto.Priority.valueOf(task.getPriority().name()),
                task.getExecutor().getName(),
                task.getDescription()
                );
    }
}
