
package com.selenium.reqres.utils;

import org.apache.poi.xwpf.usermodel.*;
import java.io.FileOutputStream;

public class WordDocGenerator {
    public static void createDoc(String title, String request, String response) {
        try (XWPFDocument doc = new XWPFDocument()) {
            XWPFParagraph heading = doc.createParagraph();
            XWPFRun run = heading.createRun();
            run.setText(title);
            run.setBold(true);
            run.setFontSize(16);

            doc.createParagraph().createRun().setText("Request: \n" + request);
            doc.createParagraph().createRun().setText("\nResponse:\n" + response);

            try (FileOutputStream out = new FileOutputStream(title + ".docx")) {
                doc.write(out);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
