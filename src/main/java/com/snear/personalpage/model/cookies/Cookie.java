package com.snear.personalpage.model.cookies;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "COOKIES")
public class Cookie {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "COOKIE_KEY")
    private String cookie_key;

    @Lob
    @Column(name = "COOKIE_VALUE", columnDefinition = "TEXT", length = 512)
    private String cookie_value;

    public Cookie(String cookie_key, String cookie_value) {
        this.cookie_key = cookie_key;
        this.cookie_value = cookie_value;
    }
}
