package com.snear.personalpage.model.cookies;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode

@Entity
@Table(name = "COOKIES")
public class Cookie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_COOKIE", nullable = false, unique = true)
    private Long idCookie;

    @JoinColumn(name = "ID_CONNECTION", nullable = false)
    @ManyToOne
    private Connection connection;

    @Column(name = "COOKIE_KEY", unique = true)
    private String cookieKey;

    @Column(name = "COOKIE_VALUE")
    private String cookieValue;


    public Cookie(Connection connection, String cookieKey, String cookieValue) {
        this.connection = connection;
        this.cookieKey = cookieKey;
        this.cookieValue = cookieValue;
    }
}
