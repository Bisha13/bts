package ru.bisha.bts.repo;

import org.springframework.data.repository.CrudRepository;
import ru.bisha.bts.entity.TaskEntity;

public interface TaskRepo extends CrudRepository<TaskEntity, Integer> {
}
