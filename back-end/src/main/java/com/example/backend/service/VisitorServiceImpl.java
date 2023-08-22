package com.example.backend.service;

import com.example.backend.domain.Visitor;
import com.example.backend.repository.VisitorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Primary
@Slf4j
@Service
@RequiredArgsConstructor
public class VisitorServiceImpl implements VisitorService{

    private final VisitorRepository visitorRepository;

    @Override
    @Transactional(readOnly = true)
    public boolean existsByIpAndCreateAt(String ip, LocalDate createAt) {
        return visitorRepository.existsByIpAndCreateAt(ip,createAt);
    }

    @Override
    @Transactional
    public void saveVisitor(Visitor visitor) {
        visitorRepository.save(visitor);
    }

}
