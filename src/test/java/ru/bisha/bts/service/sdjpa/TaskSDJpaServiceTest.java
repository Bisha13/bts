package ru.bisha.bts.service.sdjpa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import ru.bisha.bts.model.entity.Project;
import ru.bisha.bts.model.entity.Task;
import ru.bisha.bts.model.entity.User;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Task persistence test")
class TaskSDJpaServiceTest extends PersistenceTest {

    @Autowired
    TaskSDJpaService taskService;

    @Autowired
    ProjectSDJpaService projectService;

    @Autowired
    UserSDJpaService userService;

    @DisplayName("Test findAll + findById + save")
    @Test
    void findAll() {

        Set<Task> taskBefore = taskService.findAll();
        int size = taskBefore.size();

        Set<String> expected = new HashSet<>();
        expected.add("1|bts is working to good");
        expected.add("2|equals is not equals");
        expected.add("3|Make multiplayer");
        expected.add("4|Make AI");
        expected.add("5|bts need to work from json file");

        Set<String> actual = taskBefore
                .stream()
                .map(u -> u.getId() + "|" + u.getTheme())
                .collect(Collectors.toSet());

        assertEquals(expected, actual);

        Project project = projectService.findById(1);
        User user = userService.findById(1);

        Task task1 = new Task();
        task1.setTheme("Theme1");
        task1.setProject(project);
        task1.setExecutor(user);

        Task task2 = new Task();
        task2.setTheme("Theme2");
        task2.setProject(project);
        task2.setExecutor(user);

        Task task3 = new Task();
        task3.setTheme("Theme3");
        task3.setProject(project);
        task3.setExecutor(user);


        taskService.save(task1);
        taskService.save(task2);
        taskService.save(task3);

        assertNotEquals(0, taskBefore.size());
        Set<Task> tasksAfter = taskService.findAll();

        expected.add("6|Theme1");
        expected.add("7|Theme2");
        expected.add("8|Theme3");

        Set<String> actual2 = tasksAfter
                .stream()
                .map(u -> u.getId() + "|" + u.getTheme())
                .collect(Collectors.toSet());

        assertEquals(expected, actual2);

        int sizeAfter = tasksAfter.size();
        assertEquals(size + 3,  sizeAfter);

    }

    @DisplayName("Test DataIntegrityViolationException for class Task")
    @Test
    void testExceptions() {
        Task emptyTask = new Task();
        Project project = new Project();
        project.setId(1);
        User user = new User();
        user.setId(1);

        assertThrows(DataIntegrityViolationException.class, () -> {

            taskService.save(emptyTask);
        }, "Exception when empty task is saving is not thrown.");

        Task taskWithoutTheme = new Task();
        taskWithoutTheme.setExecutor(user);
        taskWithoutTheme.setProject(project);
        assertThrows(DataIntegrityViolationException.class, () -> {

            taskService.save(taskWithoutTheme);
        }, "Exception is not thrown when task without theme is saving.");

        Task taskWithoutExecutor = new Task();
        taskWithoutExecutor.setProject(project);
        taskWithoutExecutor.setTheme("Theme");
        assertThrows(DataIntegrityViolationException.class, () -> {

            taskService.save(taskWithoutExecutor);
        }, "Exception is not thrown when task without executor is saving.");

        Task fineTask = new Task();
        fineTask.setProject(project);
        fineTask.setExecutor(user);
        fineTask.setTheme("Theme");
        assertDoesNotThrow( () -> {
            taskService.save(fineTask);
        }, "Exception thrown when saving fine task.");
    }
}