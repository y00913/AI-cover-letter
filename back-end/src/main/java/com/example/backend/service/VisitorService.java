package com.example.backend.service;

import com.example.backend.domain.Visitor;

import java.time.LocalDate;

public interface VisitorService {

    boolean existsByIpAndCreateAt(String ip, LocalDate createAt);
    void saveVisitor(Visitor visitor);

}
