package ru.bisha.bts.service.save;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.bisha.bts.model.dto.ProjectDto;
import ru.bisha.bts.model.dto.TaskDto;
import ru.bisha.bts.model.dto.UserDto;
import ru.bisha.bts.service.ProjectService;
import ru.bisha.bts.service.TaskService;
import ru.bisha.bts.service.UserService;
import ru.bisha.bts.service.map.DtoMapService;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CsvFileSaverService implements FileSaverService {

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private DtoMapService dtoMapService;

    private List<UserDto> userDtoList;

    private List<ProjectDto> projectDtoList;

    private List<TaskDto> taskDtoList;

    @Override
    public void saveFileFromDb(String file) {
        initUserList();
        initProjectList();
        initTaskList();


        List<TaskDto> changedAfterUser = new ArrayList<>();

        for (int i = 0; i < userDtoList.size(); i++) {
            if (userDtoList.get(i) == null) {
                continue;
            }
            int userId = userDtoList.get(i).getId();
            changeExecutorAndAddToChangedList(changedAfterUser, i, userId);
            UserDto user = userDtoList.get(i);
            user.setId(i + 1);
            userDtoList.set(i, user);
        }

        List<TaskDto> changedAfterProject = new ArrayList<>();

        for (int i = 0; i < projectDtoList.size(); i++) {
            if (projectDtoList.get(i) == null) {
                continue;
            }
            int projectId = projectDtoList.get(i).getId();
            changeProjectAndAddToChangedList(changedAfterUser,
                    changedAfterProject, i, projectId);
            ProjectDto project = projectDtoList.get(i);
            project.setId(i + 1);
            projectDtoList.set(i, project);
        }

        writeToFile(file, changedAfterProject);
    }

    private void writeToFile(String file, List<TaskDto> changedAfterProject) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (UserDto userDto : userDtoList) {
                writer.write(userDto.toString());
                writer.newLine();
            }
            for (ProjectDto projectDto : projectDtoList) {
                writer.write(projectDto.toString());
                writer.newLine();
            }
            for (int i = 0; i < changedAfterProject.size(); i++) {
                TaskDto taskDto = changedAfterProject.get(i);
                taskDto.setId(i + 1);
                writer.write(taskDto.toString());
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initTaskList() {
        taskDtoList = taskService
                .findAll()
                .stream()
                .map(dtoMapService::mapToDto)
                .collect(Collectors.toList());
    }

    private void initProjectList() {
        projectDtoList = projectService
                .findAll()
                .stream()
                .map(dtoMapService::mapToDto)
                .collect(Collectors.toList());
    }

    private void initUserList() {
        userDtoList = userService
                .findAll()
                .stream()
                .map(dtoMapService::mapToDto)
                .collect(Collectors.toList());
    }

    private void changeProjectAndAddToChangedList(List<TaskDto> changedAfterUser,
                                                  List<TaskDto> changedAfterProject, int i, int projectId) {
        for (int pi = 0; pi < changedAfterUser.size(); pi++) {
            TaskDto taskDto = changedAfterUser.get(pi);
            if (taskDto == null) {
                continue;
            }
            if (taskDto.getProject() == projectId) {
                taskDto.setProject(i + 1);
                changedAfterProject.add(taskDto);
                changedAfterUser.set(pi, null);
            }
        }
    }


    private void changeExecutorAndAddToChangedList(List<TaskDto> changedAfterUser, int i, int userId) {
        for (int ti = 0; ti < taskDtoList.size(); ti++) {

            TaskDto taskDto = taskDtoList.get(ti);
            if (taskDto == null) {
                continue;
            }
            if (taskDto.getExecutor() == userId) {
                taskDto.setExecutor(i + 1);
                changedAfterUser.add(taskDto);
                taskDtoList.set(ti, null);
            }
        }
    }


}
