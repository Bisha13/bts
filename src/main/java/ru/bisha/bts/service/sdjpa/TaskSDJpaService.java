package ru.bisha.bts.service.sdjpa;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bisha.bts.model.entity.Task;
import ru.bisha.bts.repo.TaskRepo;
import ru.bisha.bts.service.TaskService;


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
    public Task findById(final Integer id) {
        return taskRepo.findById(id).orElse(null);
    }

    @Override
    public Task save(final Task object) {
        return taskRepo.save(object);
    }

    @Override
    public void delete(final Task object) {
        taskRepo.delete(object);
    }

    @Override
    public void deleteById(final Integer id) {
        taskRepo.deleteById(id);
    }
}
