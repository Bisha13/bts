package ru.bisha.bts.service.sdjpa;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.bisha.bts.model.entity.Task;
import ru.bisha.bts.repo.TaskRepo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TaskSDJpaServiceTest {

    @Mock
    TaskRepo taskRepo;

    @InjectMocks
    TaskSDJpaService service;

    @Test
    void testFindAll() {
        service.findAll();
        verify(taskRepo, times(1)).findAll();
    }

    @Test
    void testFindById() {
        service.findById(1);
        verify(taskRepo, times(1))
                .findById(anyInt());
    }

    @Test
    void testDelete() {
        service.delete(new Task());
        verify(taskRepo, times(1))
                .delete(any(Task.class));
    }

    @Test
    void testDeleteById() {
        service.deleteById(1);
        verify(taskRepo, times(1))
                .deleteById(anyInt());
    }
}