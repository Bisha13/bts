package ru.bisha.bts.repo;

import org.springframework.data.repository.CrudRepository;
import ru.bisha.bts.model.Task;

public interface TaskRepo extends CrudRepository<Task, Integer> {
}
