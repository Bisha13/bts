package ru.bisha.bts.fileParser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class FileParser {

    @Autowired
    private QueryExecutor queryExecutor;

    public static final String DELIMITER = "\\|";

    public void parse(String file) {

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.ready()) {
                String line = reader.readLine();
                String[] parsedLine = line.split(DELIMITER);
                queryExecutor.executeQuery(parsedLine);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
