package ru.bisha.bts.service.parser;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.bisha.bts.repo.insertrepos.ProjectInsertRepo;
import ru.bisha.bts.repo.insertrepos.TaskInsertRepo;
import ru.bisha.bts.repo.insertrepos.UserInsertRepo;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class QueryExecutorTest {

    @Mock
    UserInsertRepo userInsertRepo;

    @Mock
    ProjectInsertRepo projectInsertRepo;

    @Mock
    TaskInsertRepo taskInsertRepo;

    @InjectMocks
    QueryExecutor queryExecutor;

    @Test
    void testExecuteQuery() {
        String[] userParsedLine = {"user", "1", "Ivan"};
        String[] projectParsedLine = {"project","1","bts"};
        String[] taskParsedLine = {"task","1","bts is working to good","1","ISSUE","LOW","1"
                ,"bts is working too good, im almost got blinded by its glory, fix it!"};

        queryExecutor.executeQuery(userParsedLine);
        verify(userInsertRepo).insertWithQuery(userParsedLine);

        queryExecutor.executeQuery(projectParsedLine);
        verify(projectInsertRepo).insertWithQuery(projectParsedLine);

        queryExecutor.executeQuery(taskParsedLine);
        verify(taskInsertRepo).insertWithQuery(taskParsedLine);

    }
}