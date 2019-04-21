package com.snear.personalpage.controllers;

import org.springframework.core.io.InputStreamResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;


@RestController
@RequestMapping("/download/")
public class FileDownloadController {

    @RequestMapping(value = "CV_Aleksey_Kazarikin_Java_BE_Developer.docx", method = RequestMethod.GET)
    public InputStreamResource FileSystemResource(HttpServletResponse response) throws IOException {
        response.setContentType("application/msword");
        response.setHeader("Content-Disposition", "attachment; filename=\"CV_Aleksey_Kazarikin_Java_BE_Developer.docx\"");
        String pathDownloadFile = "/app/target/classes/static/downloads/docs/CV_Aleksey_Kazarikin_Java_BE_Developer.docx";
        InputStreamResource resource = new InputStreamResource(new FileInputStream(pathDownloadFile));
        return resource;
    }
}
