package ru.bisha.bts.repo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.bisha.bts.service.parser.FileParserService;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class DbCleanerTest {

    public static final String TEST_CSV = "src/test/resources/test.csv";

    @Autowired
    FileParserService fileParserService;

    @Autowired
    DbCleaner dbCLeaner;


    @BeforeEach
    void setUp() {
        fileParserService.parseFileToDd(TEST_CSV);
    }

    @DisplayName("Test multiple cleaning of db")
    @RepeatedTest(3)
    void deleteAllData() {

        assertDoesNotThrow(() -> {
            dbCLeaner.deleteAllData();
        });
    }
}