package com.project.shorty.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.project.shorty.service.RateLimiterService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    private final RateLimiterService rateLimiterService;

    public RateLimitInterceptor(RateLimiterService rateLimiterService) {
        this.rateLimiterService = rateLimiterService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        String ip = request.getRemoteAddr();

        if (!rateLimiterService.allowRequest(ip)) {

            response.setStatus(429);
            response.getWriter().write("Too many requests");
            return false;
        }

        return true;
    }
}
