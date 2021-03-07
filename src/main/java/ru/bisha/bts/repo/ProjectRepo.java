package ru.bisha.bts.repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.bisha.bts.entity.ProjectEntity;

public interface ProjectRepo extends CrudRepository<ProjectEntity, Integer> {

    @Modifying
    @Query(
            value = "truncate table projects",
            nativeQuery = true
    )
    void truncateMyTable();
}
