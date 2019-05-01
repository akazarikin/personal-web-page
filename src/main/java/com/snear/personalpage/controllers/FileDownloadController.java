package com.snear.personalpage.controllers;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;


@RestController
@RequestMapping("/download/")
public class FileDownloadController {

    @RequestMapping(value = "resume", method = RequestMethod.GET)
    public void FileSystemResource(HttpServletResponse response) {

        response.setContentType("application/msword");
        response.setHeader("Content-Disposition", "attachment; filename=\"CV_Aleksey_Kazarikin_Java_BE_Developer.docx\"");
        String pathDownloadFile = "/app/target/classes/static/downloads/docs/CV_doc.docx";
//        String pathDownloadFile = "src/main/resources/static/downloads/docs/CV_doc.docx";

        try {
            FileInputStream fileInputStream = new FileInputStream(pathDownloadFile);

            IOUtils.copy(fileInputStream, response.getOutputStream());
            response.flushBuffer();

        } catch (IOException ex) {
            System.out.println("Error writing file to output stream. Filename ");
            throw new RuntimeException("IOError writing file to output stream"); //TODO Logger
        }

    }
}
