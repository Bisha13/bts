package ru.bisha.bts.service.sdjpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.bisha.bts.parser.FileParser;
import ru.bisha.bts.repo.DbCLeaner;

@Tag("persistence")
@SpringBootTest
public abstract class PersistenceTest {

    public static final String TEST_CSV = "src/test/resources/test.csv";

    @Autowired
    DbCLeaner dbCLeaner;

    @Autowired
    FileParser fileParser;

    @BeforeEach
    void before() {
        dbCLeaner.deleteAllData();
        fileParser.parseFileToDd(TEST_CSV);
    }

}
