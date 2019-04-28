package com.snear.personalpage.controllers;

import com.snear.personalpage.model.cookies.Connection;
import com.snear.personalpage.model.cookies.Cookie;
import com.snear.personalpage.repositories.ConnectionRepository;
import com.snear.personalpage.repositories.CookiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WebController {

    @Autowired
    ConnectionRepository connectionRepository;

    @Autowired
    CookiesRepository cookiesRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(@RequestHeader HttpHeaders headers, HttpServletRequest request, HttpSession session) {

        String ip_address_connected_from = request.getRemoteAddr();
        session.setAttribute("ip_address", "ip: " + ip_address_connected_from);

        List<String> listCookiesFromHeaders = headers.get(HttpHeaders.COOKIE);

        String cookiesString = "";
        for (int i = 0; i < listCookiesFromHeaders.size(); i++) {
            cookiesString += listCookiesFromHeaders.get(i);
        }

        processingConnection(ip_address_connected_from, cookiesString);

        return "base";
    }

    private Connection processingConnection(String ip_address_connected_from, String cookiesString) {

        Connection connection;
        Map<String, String> cookieMap = parseRawCookie(cookiesString);
        List<Cookie> cookiesNew = new ArrayList<>();

        if (connectionRepository.existsConnectionByIpAddress(ip_address_connected_from)) {
            connection = connectionRepository.findConnectionByIpAddress(ip_address_connected_from);
            connection.incrementCount();
            cookieMap.forEach((cookie_key, cookie_value) -> {
                if (!cookiesRepository.existsCookieByCookieKey(cookie_key)) {
                    cookiesNew.add(new Cookie(connection, cookie_key, cookie_value));
                } else {
                    Cookie byCookieKey = cookiesRepository.findByCookieKey(cookie_key);
                    String byCookieKeyCookieValue = byCookieKey.getCookieValue();
                    if (!byCookieKeyCookieValue.equals(cookie_value)) byCookieKey.setCookieValue(cookie_value);
                }
            });
        } else {
            connection = new Connection().builder()
                    .countConnections(1)
                    .ipAddress(ip_address_connected_from)
                    .build();
            cookieMap.forEach((s, s2) -> cookiesNew.add(new Cookie(connection, s, s2)));
        }
        saveData(connection, cookiesNew);
        return connection;
    }

    private Map<String, String> parseRawCookie(String rawCookie) {
        String[] rawCookieParams = rawCookie.split("; ");
        Map<String, String> cookieMap = new HashMap<>();
        for (String s : rawCookieParams) {
            String[] keyAndValue = s.split("=");
            if (keyAndValue.length == 1 && !keyAndValue[0].equals("")) {
                String generatedValue = "__generated_key__:" + LocalTime.now().toString();
                cookieMap.put(generatedValue, keyAndValue[0]);
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

    private void saveData(Connection connection, List<Cookie> cookies) {
        connectionRepository.save(connection);
        if (!cookies.isEmpty()) cookiesRepository.saveAll(cookies);
    }
}
