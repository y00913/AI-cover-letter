package com.example.backend.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories
public class RedisConfig {

    @Value("${spring.data.redis.host}")
    private String host;
    @Value("${spring.data.redis.port}")
    private int port;
    @Value("${spring.data.redis.lettuce.pool.min-idle}")
    private int minIdle;
    @Value("${spring.data.redis.lettuce.pool.time-between-eviction-runs}")
    private int timeBetweenEvictionRuns;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(host, port);
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();

        poolConfig.setMinIdle(minIdle);
        poolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRuns);

        LettuceClientConfiguration clientConfig = LettucePoolingClientConfiguration.builder().poolConfig(poolConfig).build();
        LettuceConnectionFactory factory = new LettuceConnectionFactory(config, clientConfig);
        factory.setShareNativeConnection(Boolean.FALSE);

        return factory;
    }

    @Bean
    public RedisTemplate<?, ?> redisTemplate() {
        RedisTemplate<?, ?> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        return redisTemplate;
    }

}
