package com.ctrlcutter.backend.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class FileReader {

    public FileReader() {}

    public String readFile(String filePath) {
        InputStream is = getFileAsIOStream(filePath);
        String fileContent = "";
        try {
            fileContent = getFileContent(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileContent;
    }

    private InputStream getFileAsIOStream(String filePath) {

        InputStream ioStream = this.getClass().getClassLoader().getResourceAsStream(filePath);

        if (ioStream == null) {
            throw new IllegalArgumentException(filePath + " is not found");
        }
        return ioStream;
    }

    private String getFileContent(InputStream is) throws IOException {

        StringBuilder fileContentBuilder = new StringBuilder();

        try (InputStreamReader isr = new InputStreamReader(is); BufferedReader br = new BufferedReader(isr);) {

            fileContentBuilder.append(br.lines().collect(Collectors.joining(System.lineSeparator())));
            is.close();
        }

        return fileContentBuilder.toString();
    }

}
