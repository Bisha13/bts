package ru.bisha.bts.service.sdjpa;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bisha.bts.model.entity.Task;
import ru.bisha.bts.repo.TaskRepo;
import ru.bisha.bts.service.TaskService;

import javax.transaction.Transactional;

@Service
public class TaskSDJpaService implements TaskService {

    @Autowired
    private TaskRepo taskRepo;

    @Override
    public List<Task> findAll() {
        List<Task> tasks = new ArrayList<>();
        taskRepo.findAll().forEach(tasks::add);
        return tasks;
    }

    @Transactional
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