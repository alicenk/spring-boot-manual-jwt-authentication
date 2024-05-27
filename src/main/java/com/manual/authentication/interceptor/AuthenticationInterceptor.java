package com.manual.authentication.interceptor;

import com.manual.authentication.jwt.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
        HttpServletRequest request,
        HttpServletResponse response,
        Object handler
    ) {
        String token = null;
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("Bearer".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }

        if (token != null) {
            // JWT doğrulaması
            if (JwtUtil.validateToken(token) != null) {
                System.out.println("JWT Token is valid");
                System.out.println("Request URL: " + request.getRequestURL());
                System.out.println("Request Method: " + request.getMethod());
                return true;
            } else {
                System.out.println("JWT Token is invalid");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
        } else {
            System.out.println("Bearer cookie not found");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
    }
}
