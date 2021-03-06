package ru.bisha.bts.repo;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class UserInsertRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void insertWithQuery(final String[] parsedLine) {
        entityManager.createNativeQuery("INSERT INTO users (" +
                "id, " +
                "theme, " +
                "project_id, " +
                "type, " +
                "priority, " +
                "executor_id, " +
                "description) VALUES (?,?,?,?,?,?,?)")
                .setParameter(1, parsedLine[1])
                .setParameter(2, parsedLine[2])
                .setParameter(3, parsedLine[3])
                .setParameter(4, parsedLine[4])
                .setParameter(5, parsedLine[5])
                .setParameter(6, parsedLine[6])
                .setParameter(6, parsedLine[7])
                .executeUpdate();
    }
}
