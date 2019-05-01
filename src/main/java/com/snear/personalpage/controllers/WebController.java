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
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpSession;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
class WebController {

    @Autowired
    private ConnectionRepository connectionRepository;

    @Autowired
    private CookiesRepository cookiesRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(@RequestHeader HttpHeaders headers, ServletWebRequest request, HttpSession session) {

        String ip_address_connected_from = request.getRequest().getRemoteAddr();

        if (ip_address_connected_from.equals("127.0.0.1") || ip_address_connected_from.equals("0:0:0:0:0:0:0:1")) {
            session.setAttribute("ip_address", "Connected from localhost");
            System.getProperties().forEach((o, o2) -> System.out.println(o.toString() + "--------" + o2));
            return "base";
        } else if (ip_address_connected_from.equals("")) {
            session.setAttribute("ip_address", "Your ip is not available");
            return "base";
        }
        session.setAttribute("ip_address", "ip: " + ip_address_connected_from);
        List<String> listCookiesFromHeaders = headers.get(HttpHeaders.COOKIE);
        StringBuilder cookiesString = new StringBuilder();
        if (listCookiesFromHeaders != null) {
            for (String s : listCookiesFromHeaders) {
                cookiesString.append(s);
            }
        }
        processingConnection(ip_address_connected_from, cookiesString.toString());

        return "base";
    }

    private void processingConnection(String ip_address_connected_from, String cookiesString) {

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
                    Cookie byCookieKey = cookiesRepository.findByCookieKeyAndConnection(cookie_key, connection);
                    String byCookieKeyCookieValue = byCookieKey.getCookieValue();
                    if (!byCookieKeyCookieValue.equals(cookie_value)) byCookieKey.setCookieValue(cookie_value);
                }
            });
        } else {
            connection = Connection.builder()
                    .countConnections(1)
                    .ipAddress(ip_address_connected_from)
                    .build();
            cookieMap.forEach((s, s2) -> cookiesNew.add(new Cookie(connection, s, s2)));
        }

        saveData(connection, cookiesNew);

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
