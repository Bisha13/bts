package ru.bisha.bts.repo;

import org.springframework.data.repository.CrudRepository;
import ru.bisha.bts.model.Project;

public interface ProjectRepo extends CrudRepository<Project, Integer> {
}
