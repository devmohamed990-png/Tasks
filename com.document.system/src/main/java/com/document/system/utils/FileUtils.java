package com.document.system.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class FileUtils {

    public static Path createFolder(String filePath) {
        File file = new File(filePath);
        if(!file.exists()) {
            file.mkdirs();
        }
        return file.toPath();
    }
}
