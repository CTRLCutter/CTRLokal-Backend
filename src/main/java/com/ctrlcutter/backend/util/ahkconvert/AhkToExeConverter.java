package com.ctrlcutter.backend.util.ahkconvert;

import java.io.IOException;

public class AhkToExeConverter {

    private final String ahkConverterPath = "";

    public AhkToExeConverter() {

    }

    public void convertAhkToExeFile(String inputPath, String outputPath) throws IOException {
        Runtime.getRuntime().exec(ahkConverterPath + " /in " + inputPath + " /out " + outputPath + " /base " + ahkConverterPath);
    }
}
