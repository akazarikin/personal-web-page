package com.snear.personalpage.model.cookies;

import com.snear.personalpage.model.BaseEntity;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

@Entity
@Table(name = "COOKIES")
public class Cookie extends BaseEntity {

    @JoinColumn(name = "ID_CONNECTION", nullable = false)
    @ManyToOne
    private Connection connection;

    @Column(name = "COOKIE_KEY")
    private String cookie_key;

    @Column(name = "COOKIE_VALUE")
    private String cookie_value;

}
