package ru.bisha.bts.repo.insertrepos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class TaskInsertRepo {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void insertWithQuery(final String[] parsedLine) {
        entityManager.createNativeQuery("INSERT INTO tasks ("
                //"id, " +
                + "theme, "
                + "project_id, "
                + "type, "
                + "priority, "
                + "executor_id, "
                + "description) VALUES (?,?,?,?,?,?)")
                .setParameter(1, parsedLine[2])
                .setParameter(2, parsedLine[3])
                .setParameter(3, parsedLine[4])
                .setParameter(4, parsedLine[5])
                .setParameter(5, parsedLine[6])
                .setParameter(6, parsedLine[7])
                .executeUpdate();
    }
}
