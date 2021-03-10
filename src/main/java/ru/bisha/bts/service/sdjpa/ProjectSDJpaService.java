package ru.bisha.bts.service.sdjpa;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bisha.bts.model.entity.Project;
import ru.bisha.bts.repo.ProjectRepo;
import ru.bisha.bts.service.ProjectService;
import javax.transaction.Transactional;

@Service
public class ProjectSDJpaService implements ProjectService {

    @Autowired
    private ProjectRepo projectRepo;

    @Override
    public List<Project> findAll() {
        List<Project> projects = new ArrayList<>();
        projectRepo.findAll().forEach(projects::add);
        return projects;
    }

    @Transactional
    @Override
    public Project findById(Integer id) {
        return projectRepo.findById(id).orElse(null);
    }

    @Override
    public Project save(Project object) {
        return projectRepo.save(object);
    }

    @Override
    public void delete(Project object) {
        projectRepo.delete(object);
    }

    @Override
    public void deleteById(Integer id) {
        projectRepo.deleteById(id);
    }
}
