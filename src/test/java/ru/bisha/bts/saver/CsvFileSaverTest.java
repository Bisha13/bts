package ru.bisha.bts.saver;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.bisha.bts.model.entity.Project;
import ru.bisha.bts.parser.FileParser;
import ru.bisha.bts.repo.DbCleaner;
import ru.bisha.bts.service.ProjectService;
import ru.bisha.bts.service.UserService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CsvFileSaverTest {

    public static final String OUT = "src/test/resources/testOut.csv";
    public static final String IN = "src/test/resources/test.csv";

    @Autowired
    CsvFileSaver fileSaver;

    @Autowired
    ProjectService projectService;

    @Autowired
    UserService userService;

    @Autowired
    FileParser fileParser;

    @Autowired
    DbCleaner dbCleaner;


    @BeforeAll
    void beforeAll() {
        dbCleaner.deleteAllData();
        fileParser.parseFileToDd(IN);
    }

    @Test
    void saveFileFromDbWhenUserDeleted() {
        userService.deleteById(1);
        fileSaver.saveFileFromDb(OUT);
    }

    @Test
    void saveFileFromDbWhenProjectDeleted() {
        projectService.deleteById(1);
        fileSaver.saveFileFromDb(OUT);
    }
}