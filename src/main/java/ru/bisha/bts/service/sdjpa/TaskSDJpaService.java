package ru.bisha.bts.service.sdjpa;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bisha.bts.model.Task;
import ru.bisha.bts.repo.TaskRepo;
import ru.bisha.bts.service.TaskService;

@Service
public class TaskSDJpaService implements TaskService {

    @Autowired
    private TaskRepo taskRepo;

    @Override
    public Set<Task> findAll() {
        Set<Task> tasks = new HashSet<>();
        taskRepo.findAll().forEach(tasks::add);
        return tasks;
    }

    @Override
    public Task findById(Integer id) {
        return taskRepo.findById(id).orElse(null);
    }

    @Override
    public Task save(Task object) {
        return taskRepo.save(object);
    }

    @Override
    public void delete(Task object) {
        taskRepo.delete(object);
    }

    @Override
    public void deleteById(Integer id) {
        taskRepo.deleteById(id);
    }
}