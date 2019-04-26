package com.snear.personalpage.model.cookies;

import com.snear.personalpage.model.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

@Entity
@Table(name = "CONNECTIONS")
public class Connection extends BaseEntity {

    @Column(name = "IP_ADDRESS", length = 20, nullable = false)
    private String ipAddress;
}

