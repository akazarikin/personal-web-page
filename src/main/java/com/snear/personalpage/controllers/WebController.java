package com.snear.personalpage.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;


@Controller
public class WebController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(@RequestHeader HttpHeaders headers, HttpServletRequest request) {


//        Connection connection = new Connection();
        String ip_address_connected_from = request.getRemoteAddr();
        System.out.println("================================");
        System.out.println(ip_address_connected_from);
        System.out.println("================================");

        return "base";
    }
}