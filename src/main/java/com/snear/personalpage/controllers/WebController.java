package com.snear.personalpage.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class WebController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(@RequestHeader HttpHeaders headers) {
        System.out.println("=============================");
        System.out.println(headers.getLocation());
        System.out.println("=============================");

        return "base";
    }
}