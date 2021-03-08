package ru.bisha.bts.repo;

import org.springframework.data.repository.CrudRepository;
import ru.bisha.bts.model.TaskEntity;

public interface TaskRepo extends CrudRepository<TaskEntity, Integer> {
}
