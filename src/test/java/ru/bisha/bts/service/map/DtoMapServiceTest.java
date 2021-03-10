package ru.bisha.bts.service.map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import ru.bisha.bts.model.dto.ProjectDto;
import ru.bisha.bts.model.dto.TaskDto;
import ru.bisha.bts.model.dto.UserDto;
import ru.bisha.bts.model.entity.Project;
import ru.bisha.bts.model.entity.Task;
import ru.bisha.bts.model.entity.User;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DtoMapServiceTest {

    private DtoMapService mapService = new DtoMapService();

    private Project project;
    private User user;
    private Task task;

    @BeforeAll
    void setUp() {
        project = new Project();
        String projectName = "NewProjectName";
        int projectId = 1;
        project.setName(projectName);
        project.setId(projectId);

        user = new User();
        String userName = "NewUserName";
        int userId = 1;
        user.setId(userId);
        user.setName(userName);

        task = new Task();
        int taskId = 1;
        String taskTheme = "NewTaskTheme";
        Task.Type taskType = Task.Type.TASK;
        Task.Priority taskPriority = Task.Priority.HIGH;
        String description = "New Description";
        task.setId(taskId);
        task.setTheme(taskTheme);
        task.setProject(project);
        task.setExecutor(user);
        task.setDescription(description);
        task.setPriority(taskPriority);
        task.setType(taskType);

    }

    @Test
    void testProjectMapping() {
        ProjectDto projectDto = mapService.mapToDto(project);

        assertAll("ProjectDto maping test",
                () -> assertEquals(project.getId(),
                        projectDto.getId(), "Id does not match"),
                () -> assertEquals(project.getName(),
                        projectDto.getName(), "Theme does not match")
        );

    }

    @Test
    void testUserMapping() {
        UserDto userDto = mapService.mapToDto(user);

        assertAll("USerDto maping test",
                () -> assertEquals(user.getId(),
                        userDto.getId(), "Id does not match"),
                () -> assertEquals(user.getName(),
                        userDto.getName(), "Theme does not match")
        );

    }

    @Test
    void testTaskMapping() {

        TaskDto taskDto =  mapService.mapToDto(task);

        assertAll("TaskDto maping test",
                () -> assertEquals(task.getId(),
                        taskDto.getId(), "Id does not match"),
                () -> assertEquals(task.getTheme(),
                        taskDto.getTheme(), "Theme does not match"),
                () -> assertEquals(task.getProject().
                        getName(), taskDto.getProject().getName(), "Project names does not match"),
                () -> assertEquals(task.getType().name(),
                        taskDto.getType().name(), "Type does not match"),
                () -> assertEquals(task.getPriority().name(),
                        taskDto.getPriority().name(), "Priority does not match"),
                () -> assertEquals(task.getExecutor().getName(),
                        taskDto.getExecutor().getName(), "Executor name does not match"),
                () -> assertEquals(task.getDescription(),
                        taskDto.getDescription(), "Description does not match")
        );
    }
}