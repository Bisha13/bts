package ru.bisha.bts.service.parser;

/**
 * Accepts String filepath, and parses its content to database.
 */
public interface FileParserService {
    void parseFileToDd(String file);
}
