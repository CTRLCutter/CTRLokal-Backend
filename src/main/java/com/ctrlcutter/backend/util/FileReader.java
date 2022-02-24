package com.ctrlcutter.backend.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    /**
     * This method gives out a string with the complete content of a file.
     * 
     * @param filePath The path of the file.
     * @return Returns the complete content of the file.
     */
    public static String readCompleteFile(String filePath) {
        List<String> fileLines = readCompleteFileLineSeparated(filePath);
        StringBuilder fileBuilder = new StringBuilder();

        for (String fileLine : fileLines) {
            fileBuilder.append(fileLine);

            if (!fileLine.equals(fileLines.get(fileLines.size() - 1))) {
                fileBuilder.append("\n");
            }
        }

        return fileBuilder.toString();
    }

    /**
     * This method reads a file and returns the file content line by line in a list.
     * 
     * @param filePath The path of the file.
     * @return Returns a list of strings with the corresponding line content of the file.
     */
    public static List<String> readCompleteFileLineSeparated(String filePath) {
        List<String> fileLines = new ArrayList<>();

        try {
            fileLines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileLines;
    }

}
