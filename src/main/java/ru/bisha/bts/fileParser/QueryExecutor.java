package ru.bisha.bts.fileParser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.bisha.bts.repo.UserInsertRepo;

@Component
public class QueryExecutor {

    @Autowired
    UserInsertRepo userInsertRepo;

    public void executeQuery(String[] parsedLine) {
        if (parsedLine[0].equals("user")) {
            userInsertRepo.insertWithQuery(parsedLine);
        }
    }
}
