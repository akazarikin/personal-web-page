package com.snear.personalpage.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class WebController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(@CookieValue(value = "myCookieName", defaultValue = "defaultCookieValue") String cookieValue) {

        System.out.println(cookieValue);

        return "base";
    }
}