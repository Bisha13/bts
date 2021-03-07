package ru.bisha.bts.repo;

import org.springframework.data.repository.CrudRepository;
import ru.bisha.bts.entity.ProjectEntity;

public interface ProjectRepo extends CrudRepository<ProjectEntity, Integer> {
}
