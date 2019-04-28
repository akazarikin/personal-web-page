package com.snear.personalpage.repositories;

import com.snear.personalpage.model.cookies.Connection;
import com.snear.personalpage.model.cookies.Cookie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CookiesRepository extends JpaRepository<Cookie, Long> {
    boolean existsCookieByCookieKey(String s);
    Cookie findByCookieKeyAndConnection(String s, Connection connection);

    boolean existsCookieByCookieValue(String s);

    List<Cookie> findAllByConnection(Connection connection);
}

