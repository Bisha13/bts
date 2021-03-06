package ru.bisha.bts.repo;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class ProjectInsertRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void insertWithQuery(final String[] parsedLine) {
        entityManager.createNativeQuery("INSERT INTO projects (id, name) VALUES (?,?)")
                .setParameter(1, parsedLine[1])
                .setParameter(2, parsedLine[2])
                .executeUpdate();
    }
}
