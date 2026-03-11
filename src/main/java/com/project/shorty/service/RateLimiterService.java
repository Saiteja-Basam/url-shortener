package com.project.shorty.service;

import java.time.Duration;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RateLimiterService {

    private final StringRedisTemplate redisTemplate;

    private static final int BUCKET_CAPACITY = 10;
    private static final int REFILL_TIME = 60;

    public RateLimiterService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public boolean allowRequest(String key) {
    	 String redisKey = "rate_limit:" + key;

    	    String value = redisTemplate.opsForValue().get(redisKey);

    	    if (value == null) {
    	        redisTemplate.opsForValue().set(
    	                redisKey,
    	                String.valueOf(BUCKET_CAPACITY - 1),
    	                Duration.ofSeconds(REFILL_TIME)
    	        );
    	        return true;
    	    }

    	    Long tokens = redisTemplate.opsForValue().decrement(redisKey);

    	    if (tokens < 0) {
    	        redisTemplate.opsForValue().increment(redisKey);
    	        return false;
    	    }

    	    return true;
    }
}
