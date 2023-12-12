package com.document.system.utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class PDFUtils {

    public static Path createPDFFile(String filePath) {
        try {
            File file = new File(filePath);
            if(!file.exists()) {
                file.createNewFile();
            }
            PDDocument  document = new PDDocument();
            document.addPage(new PDPage());
            document.save(filePath);
            document.close();
            return file.toPath();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("an error occurred, Please, try another file");
        }
    }
}
