package ru.bisha.bts.parser;

/**
 * Accepts String filepath, and parses its content to database.
 */
public interface FileParser {
    void parseFileToDd(String file);
}
