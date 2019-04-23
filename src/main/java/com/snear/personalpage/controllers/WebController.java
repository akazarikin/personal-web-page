package com.snear.personalpage.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.schema.Model;

import java.security.Principal;

@Controller
public class WebController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Principal principal, @CookieValue(value = "myCookieName", defaultValue = "defaultCookieValue") String cookieValue, Model model) {

        System.out.println(cookieValue);

        if (principal != null) {
            System.out.println(principal.toString());
        } else if (model != null) {
            System.out.println(model.toString());
        } else {
            System.out.println("principal = null");
        }

//        return principal != null ? "home/homeSignedIn" : "home/homeNotSignedIn";
        return "index";
    }
}