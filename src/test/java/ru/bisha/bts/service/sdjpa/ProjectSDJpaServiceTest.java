package ru.bisha.bts.service.sdjpa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.bisha.bts.model.entity.Project;
import ru.bisha.bts.model.entity.Task;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Project persistence test")
class ProjectSDJpaServiceTest extends PersistenceTest {

    @Autowired
    ProjectSDJpaService projectService;

    @Autowired
    TaskSDJpaService taskService;

    @DisplayName("Test findAll + save")
    @Test
    void findAll() {

        Set<Project> projectBefore = projectService.findAll();
        int size = projectBefore.size();

        Set<String> expected = new HashSet<>();
        expected.add("1|bts");
        expected.add("2|calculator");
        expected.add("3|XO");

        Set<String> actual = projectBefore
                .stream()
                .map(u -> u.getId() + "|" + u.getName())
                .collect(Collectors.toSet());

        assertEquals(expected, actual);

        Project project1 = new Project();
        project1.setName("Name1");
        Project project2 = new Project();
        project2.setName("Name2");
        Project project3 = new Project();
        project3.setName("Name3");
        projectService.save(project1);
        projectService.save(project2);
        projectService.save(project3);

        assertNotEquals(0, projectBefore.size());
        Set<Project> projectsAfter = projectService.findAll();

        expected.add("4|Name1");
        expected.add("5|Name2");
        expected.add("6|Name3");

        Set<String> actual2 = projectsAfter
                .stream()
                .map(u -> u.getId() + "|" + u.getName())
                .collect(Collectors.toSet());

        assertEquals(expected, actual2);

        int sizeAfter = projectsAfter.size();
        assertEquals(size + 3,  sizeAfter);

    }

    @DisplayName("Test save + delete")
    @Test
    void delete() {
        Project project = new Project();
        project.setName("Test Project");
        int sizeBefore = projectService.findAll().size();
        Project testProject = projectService.save(project);
        assertEquals(testProject.getName(), project.getName());
        int sizeAfter = projectService.findAll().size();
        assertEquals(sizeBefore + 1, sizeAfter);

        projectService.delete(testProject);
        assertNull(projectService.findById(testProject.getId()));
    }

    @DisplayName("Test deleteById + findById")
    @Test
    void deleteById() {
        int sizeBefore = projectService.findAll().size();
        projectService.deleteById(1);
        int sizeAfter = projectService.findAll().size();

        assertEquals(sizeBefore -1, sizeAfter,
                "Assert size after deletion");

        Project project = projectService.findById(1);
        assertNull(project);
    }


    @Transactional // I DON'T like it here, but for now i cant find better way to do it.
    @DisplayName("Test get all tasks in project")
    @Test
    void testGetAllTasks() {
        List<Task> expectedTaskForId1 = new ArrayList<>();

        //There are tasks 1 and 5 in test.csv for project id 1.
        expectedTaskForId1.add(taskService.findById(1));
        expectedTaskForId1.add(taskService.findById(5));

        List<Task> actualListForId1
                = projectService.findById(1).getTasks();
        assertEquals(expectedTaskForId1, actualListForId1);
    }
}