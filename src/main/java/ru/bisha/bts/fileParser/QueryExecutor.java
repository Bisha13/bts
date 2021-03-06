package ru.bisha.bts.fileParser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.bisha.bts.repo.UserInsertRepo;

import javax.transaction.Transactional;

@Component
public class QueryExecutor {

    @Autowired
    private UserInsertRepo userInsertRepo;

    @Transactional
    public void executeQuery(final String[] parsedLine) {
        if (parsedLine[0].equals("user")) {
            userInsertRepo.insertWithQuery(parsedLine);
        }
    }
}
