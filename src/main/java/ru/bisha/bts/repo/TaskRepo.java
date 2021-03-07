package ru.bisha.bts.repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.bisha.bts.entity.TaskEntity;

public interface TaskRepo extends CrudRepository<TaskEntity, Integer> {

    @Modifying
    @Query(
            value = "truncate table tasks",
            nativeQuery = true
    )
    void truncateMyTable();
}
