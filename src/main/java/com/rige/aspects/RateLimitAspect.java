package com.rige.aspects;

import com.rige.annotations.RateLimit;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import io.github.bucket4j.Bucket;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.Duration;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Component
public class RateLimitAspect {

    private final Map<String, Bucket> bucketCache = new ConcurrentHashMap<>();

    @Around("@within(rateLimit) || @annotation(rateLimit)")
    public Object rateLimit(ProceedingJoinPoint joinPoint, RateLimit rateLimit) throws Throwable {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        String key = getKey(request);
        Bucket bucket = bucketCache.get(key);

        if (bucket == null) {
            bucket = Bucket.builder()
                    .addLimit(limit -> limit
                            .capacity(rateLimit.limit())
                            .refillGreedy(10, Duration.ofSeconds(rateLimit.durationSeconds())))
                    .build();
            bucketCache.put(key, bucket);
        }

        if (bucket.tryConsume(1)) {
            return joinPoint.proceed();
        } else {
            HttpServletResponse response = (
                    (ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.
                    getRequestAttributes())).
                    getResponse();
            if (response != null) {
                response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
                response.setContentType("application/json");
                response.getWriter().write("{\"error\": \"Too many requests, try again later.\"}");
                response.getWriter().flush();
            }
            return null;
        }
    }

    private String getKey(HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        return ip + ":" + method + ":" + uri;
    }
}
