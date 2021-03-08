package ru.bisha.bts.service.sdjpa;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bisha.bts.model.ProjectEntity;
import ru.bisha.bts.repo.ProjectRepo;
import ru.bisha.bts.service.ProjectService;

@Service
public class ProjectSDJpaService implements ProjectService {

    @Autowired
    private ProjectRepo projectRepo;

    @Override
    public Set<ProjectEntity> findAll() {
        Set<ProjectEntity> projects = new HashSet<>();
        projectRepo.findAll().forEach(projects::add);
        return projects;
    }

    @Override
    public ProjectEntity findById(Integer id) {
        return projectRepo.findById(id).orElse(null);
    }

    @Override
    public ProjectEntity save(ProjectEntity object) {
        return projectRepo.save(object);
    }

    @Override
    public void delete(ProjectEntity object) {
        projectRepo.delete(object);
    }

    @Override
    public void deleteById(Integer id) {
        projectRepo.deleteById(id);
    }
}
