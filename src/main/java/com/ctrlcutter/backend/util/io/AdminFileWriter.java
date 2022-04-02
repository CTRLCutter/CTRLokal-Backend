package com.ctrlcutter.backend.util.io;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class AdminFileWriter {

    public AdminFileWriter() {}

    public void writeFileToAutostartFolder(String fileContent, String fileName) {

        try {
            String filePath = getFileFromResource("iohandler.jar");
            Runtime.getRuntime().exec("powershell.exe Start-Process -FilePath java.exe -Argument '-jar " + filePath + " -a false -p " + fileName + " -c \""
                    + fileContent + "\"' -verb RunAs");
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    private String getFileFromResource(String fileName) throws URISyntaxException {

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {

            return new File(resource.toURI()).toPath().toString();
        }
    }
}
