package com.snear.personalpage.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.HttpCookie;
import java.util.List;
import java.util.Map;

@Controller
public class WebController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(@RequestHeader HttpHeaders headers, @RequestParam Map<String, String> allParams) {
        System.out.println("=================================================================================");
        System.out.println(headers.getLocation());
        System.out.println("=================================================================================");
        headers.entrySet().forEach(stringListEntry -> System.out.println(stringListEntry));
        System.out.println("=================================================================================");
        System.out.println("=================================================================================");
        allParams.forEach((s, s2) -> System.out.println("key : " + s + ";  " + "Value : " + s2));
        System.out.println("=================================================================================");
        System.out.println("=================================================================================");

//        List<HttpCookie> cookies = HttpCookie.parse(headers.);
//        cookies.forEach(httpCookie -> System.out.println(httpCookie));
        System.out.println("=================================================================================");
        System.out.println("=================================================================================");


        return "base";
    }
}