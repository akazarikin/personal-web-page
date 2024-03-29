package com.snear.personalpage.repositories;

import com.snear.personalpage.model.cookies.Connection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConnectionRepository extends JpaRepository<Connection, Long> {
    boolean existsConnectionByIpAddress(String s);
    Connection findConnectionByIpAddress(String s);
}
