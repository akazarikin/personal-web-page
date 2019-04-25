package com.snear.personalpage.controllers;

import com.snear.personalpage.model.cookies.Connection;
import com.snear.personalpage.model.cookies.Cookie;
import com.snear.personalpage.repositories.ConnectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class WebController {

    @Autowired
    ConnectionRepository connectionRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(@RequestHeader HttpHeaders headers, HttpServletRequest request, HttpSession session) {

        String ip_address_connected_from = request.getRemoteAddr();
        session.setAttribute("ip_address", "ip: " + ip_address_connected_from);


        Connection connection = new Connection();
        connection.setIpAddress(ip_address_connected_from);


        String cookiesString = headers.get(HttpHeaders.COOKIE).toString();
        Map<String, String> cookieMap = parseRawCookie(cookiesString);
        cookieMap.forEach((s, s2) -> connection.getCookies().add(new Cookie(s, s2)));


        connectionRepository.save(connection);

        return "base";
    }


    private Map<String, String> parseRawCookie(String rawCookie) {

        String[] rawCookieParams = rawCookie.split("; ");
        Map<String, String> cookieMap = new HashMap<>();

        for (String s : rawCookieParams) {
            String[] keyAndValue = s.split("=");
            if (keyAndValue.length == 1) {
                cookieMap.put(null, keyAndValue[0]);
            } else if (keyAndValue.length == 2) {
                cookieMap.put(keyAndValue[0], keyAndValue[1]);
            } else if (keyAndValue.length > 2) {
                for (int i = 2; i < keyAndValue.length - 1; i++) {
                    keyAndValue[1] += keyAndValue[i];
                }
                cookieMap.put(keyAndValue[0], keyAndValue[1]);
            }
        }

        return cookieMap;
    }

}