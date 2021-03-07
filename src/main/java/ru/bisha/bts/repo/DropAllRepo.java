package ru.bisha.bts.repo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class DropAllRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void deleteFromTasks() {
        entityManager.createNativeQuery("DELETE FROM tasks")
                .executeUpdate();

    }

    @Transactional
    public void deleteFromUsers() {
        entityManager.createNativeQuery("DELETE FROM users")
                .executeUpdate();
    }

    @Transactional
    public void deleteFromProjects() {
        entityManager.createNativeQuery("DELETE FROM projects")
                .executeUpdate();
    }

    @Transactional
    public void deleteAllData() {
        deleteFromTasks();
        deleteFromUsers();
        deleteFromProjects();
    }
}
