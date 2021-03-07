package ru.bisha.bts.repo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DropAllRepo {

    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private UserRepo userRepo;

    public void deleteAllData() {
        taskRepo.truncateMyTable();
        projectRepo.truncateMyTable();
        userRepo.truncateMyTable();
    }

}
