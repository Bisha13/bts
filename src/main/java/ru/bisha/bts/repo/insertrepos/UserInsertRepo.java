package ru.bisha.bts.repo.insertrepos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class UserInsertRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void insertWithQuery(final String[] parsedLine) {
        entityManager.createNativeQuery("INSERT INTO users (name) VALUES (?)")
                //.setParameter(1, parsedLine[1])
                //.setParameter(2, parsedLine[2])
                .setParameter(1, parsedLine[2])
                .executeUpdate();
    }
}
