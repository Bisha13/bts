package ru.bisha.bts.service.sdjpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.bisha.bts.service.parser.FileParserService;
import ru.bisha.bts.repo.DbCleaner;

@Tag("persistence")
@SpringBootTest
public abstract class PersistenceTest {

    public static final String TEST_CSV = "src/test/resources/test.csv";

    @Autowired
    DbCleaner dbCLeaner;

    @Autowired
    FileParserService fileParserService;

    @BeforeEach
    void before() {
        dbCLeaner.deleteAllData();
        fileParserService.parseFileToDd(TEST_CSV);
    }

}
