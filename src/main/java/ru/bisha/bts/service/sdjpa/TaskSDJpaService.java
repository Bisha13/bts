package ru.bisha.bts.service.sdjpa;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bisha.bts.model.TaskEntity;
import ru.bisha.bts.repo.TaskRepo;
import ru.bisha.bts.service.TaskService;

@Service
public class TaskSDJpaService implements TaskService {

    @Autowired
    private TaskRepo taskRepo;

    @Override
    public Set<TaskEntity> findAll() {
        Set<TaskEntity> tasks = new HashSet<>();
        taskRepo.findAll().forEach(tasks::add);
        return tasks;
    }

    @Override
    public TaskEntity findById(Integer id) {
        return taskRepo.findById(id).orElse(null);
    }

    @Override
    public TaskEntity save(TaskEntity object) {
        return taskRepo.save(object);
    }

    @Override
    public void delete(TaskEntity object) {
        taskRepo.delete(object);
    }

    @Override
    public void deleteById(Integer id) {
        taskRepo.deleteById(id);
    }
}