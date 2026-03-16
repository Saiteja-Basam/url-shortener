package com.project.shorty.service;

import java.util.Collections;

import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

@Service
public class RateLimiterService {

    private final StringRedisTemplate redisTemplate;
    private final DefaultRedisScript<Long> script;

    private static final int LIMIT = 10;
    private static final int WINDOW = 60;

    public RateLimiterService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;

        script = new DefaultRedisScript<>();
        script.setLocation(new ClassPathResource("scripts/slidingWindow.lua"));
        script.setResultType(Long.class);
    }

    public boolean allowRequest(String key) {

        String redisKey = "rate_limit:" + key;

        long currentTime = System.currentTimeMillis() / 1000;

        Long result = redisTemplate.execute(
                script,
                Collections.singletonList(redisKey),
                String.valueOf(LIMIT),
                String.valueOf(WINDOW),
                String.valueOf(currentTime)
        );

        return result != null && result == 1;
    }
}
