package com.snear.personalpage.repositories;

import com.snear.personalpage.model.cookies.Cookie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CookiesRepository extends JpaRepository<Cookie, Long> {
}
