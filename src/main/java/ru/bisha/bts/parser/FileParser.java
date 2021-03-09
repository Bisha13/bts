package ru.bisha.bts.parser;

import java.io.File;

/**
 * Accepts String filepath, and parses its content to database.
 */
public interface FileParser {
    void parseFileToDd(File file);
}
