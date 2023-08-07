package com.example.backend.repository;

import com.example.backend.domain.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface VisitorRepository extends JpaRepository<Visitor, String> {

    boolean existsByIpAndCreateAt(String ip, LocalDate createAt);

}
