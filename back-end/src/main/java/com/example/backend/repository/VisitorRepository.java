package com.example.backend.repository;

import com.example.backend.domain.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitorRepository extends JpaRepository<Visitor, String> {
}
