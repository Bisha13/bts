package ru.bisha.bts.service.sdjpa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.bisha.bts.entity.ProjectEntity;
import ru.bisha.bts.repo.PersistenceTest;
import ru.bisha.bts.service.ProjectService;

import static org.junit.jupiter.api.Assertions.*;


class ProjectServiceTest extends PersistenceTest {

    @Autowired
    ProjectService projectService;

    @DisplayName("Test save and delete")
    @Test
    void delete() {
        ProjectEntity project = new ProjectEntity();
        project.setName("Test Project");
        int sizeBefore = projectService.findAll().size();
        ProjectEntity testProject = projectService.save(project);
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

        ProjectEntity project = projectService.findById(1);
        assertNull(project);
    }
}