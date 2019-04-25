package com.snear.personalpage.model.cookies;


import com.snear.personalpage.model.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "CONNECTIONS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class Connection extends BaseEntity {

    @Column(name = "IP_ADDRESS")
    private String ipAddress;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "connection_Cookie", joinColumns = @JoinColumn(name = "ipAddress"), inverseJoinColumns = @JoinColumn(name = "cookie"))
//    private List<Cookie> cookies = new ArrayList<>();
    private Set<Cookie> cookies = new HashSet<Cookie>();

}

