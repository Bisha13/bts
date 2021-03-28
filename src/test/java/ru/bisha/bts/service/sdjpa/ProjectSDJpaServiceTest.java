package ru.bisha.bts.service.sdjpa;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.bisha.bts.model.entity.Project;
import ru.bisha.bts.repo.ProjectRepo;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjectSDJpaServiceTest {

    @Mock
    ProjectRepo projectRepo;

    @InjectMocks
    ProjectSDJpaService service;

    @Test
    void testFindAll() {
        service.findAll();
        verify(projectRepo, times(1)).findAll();
    }

    @Test
    void testFindById() {
        service.findById(1);
        verify(projectRepo, times(1))
                .findById(anyInt());
    }

    @Test
    void testDelete() {
        service.delete(new Project());
        verify(projectRepo, times(1))
                .delete(any(Project.class));
    }

    @Test
    void testDeleteById() {
        service.deleteById(1);
        verify(projectRepo, times(1))
                .deleteById(anyInt());
    }
}