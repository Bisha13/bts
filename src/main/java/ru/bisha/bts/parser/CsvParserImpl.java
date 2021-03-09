package ru.bisha.bts.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Purpose of this file is to parse CSV file
 * and via queryExecutor insert info to db.
 */
@Component
public final class CsvParserImpl implements FileParser {

    @Autowired
    private QueryExecutor queryExecutor;

    public static final String DELIMITER = "\\|";

    @Override
    public void parseFileToDd(final File file) {

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.ready()) {
                String line = reader.readLine();
                String[] parsedLine = line.split(DELIMITER);
                queryExecutor.executeQuery(parsedLine);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
