package ru.bisha.bts.service.sdjpa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.bisha.bts.model.Project;
import ru.bisha.bts.repo.PersistenceTest;

import static org.junit.jupiter.api.Assertions.*;


class ProjectServiceTest extends PersistenceTest {

    @Autowired
    ProjectSDJpaService projectService;

    @DisplayName("Test save and delete")
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

    @DisplayName("Test deleteById and findById")
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
}