package com.example.backend.scheduler;

import com.example.backend.domain.Visitor;
import com.example.backend.service.VisitorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class VisitorScheduler {

    private final RedisTemplate<String, String> redisTemplate;
    private final VisitorService visitorService;

    @Scheduled(initialDelay = 3000000, fixedDelay = 3000000)
    public void updateVisitor(){
        redisTemplate.keys("*_*").forEach(key -> {
            String[] parts = key.split("_");
            String ip = parts[0];
            LocalDate createAt = LocalDate.parse(parts[1]);
            String userAgent = redisTemplate.opsForValue().get(key);

            if(!visitorService.existsByIpAndCreateAt(ip, createAt)) {
                Visitor visitor = Visitor.builder()
                        .ip(ip)
                        .userAgent(userAgent)
                        .createAt(createAt)
                        .build();
                visitorService.saveVisitor(visitor);
            }

            redisTemplate.delete(key);
        });
    }

}
