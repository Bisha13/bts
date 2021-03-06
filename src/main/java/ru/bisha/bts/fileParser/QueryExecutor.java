package ru.bisha.bts.fileParser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.bisha.bts.repo.ProjectInsertRepo;
import ru.bisha.bts.repo.TaskInsertRepo;
import ru.bisha.bts.repo.UserInsertRepo;

import javax.transaction.Transactional;

@Component
public class QueryExecutor {

    @Autowired
    private UserInsertRepo userInsertRepo;

    @Autowired
    private ProjectInsertRepo projectInsertRepo;

    @Autowired
    private TaskInsertRepo taskInsertRepo;


    @Transactional
    public void executeQuery(final String[] parsedLine) {
        if (parsedLine[0].equals("user")) {
            userInsertRepo.insertWithQuery(parsedLine);
        } else if (parsedLine[0].equals("project")) {
            projectInsertRepo.insertWithQuery(parsedLine);
        } else if (parsedLine[0].equals("task")) {
            taskInsertRepo.insertWithQuery(parsedLine);
        }
    }
}