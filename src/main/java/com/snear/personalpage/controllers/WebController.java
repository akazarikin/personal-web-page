package com.snear.personalpage.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class WebController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(@RequestHeader HttpHeaders headers, HttpServletRequest request, HttpSession session) {


        String ip_address_connected_from = request.getRemoteAddr();

        session.setAttribute("ip_address", "ip: " + ip_address_connected_from);


        return "base";
    }
}