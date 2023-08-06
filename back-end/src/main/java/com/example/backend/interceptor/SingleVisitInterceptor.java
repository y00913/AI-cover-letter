package com.example.backend.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDate;

import com.example.backend.utils.ClientUtils;

@Slf4j
@Component
@RequiredArgsConstructor
public class SingleVisitInterceptor implements HandlerInterceptor {

    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ip = ClientUtils.getIp(request);
        String userAgent = request.getHeader("User-Agent");
        String today = LocalDate.now().toString();

        if(ip.indexOf(",") >= 0) {
            ip = ip.substring(0,ip.indexOf(","));
        }
        String key = ip + "_" + today;
        log.info("key : " + key);

        ValueOperations valueOperations = redisTemplate.opsForValue();

        if(!valueOperations.getOperations().hasKey(key)) {
            valueOperations.set(key, userAgent);
        }

        return true;
    }

}
