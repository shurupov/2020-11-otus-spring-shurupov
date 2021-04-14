package ru.otus.shurupov.spring.docker.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class SpaAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        onAuthenticationSuccess(request, response, authentication);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String jsonResponse = "{\"status\":\"success\",\"message\":\"You have successfully authenticated\"}";
        response.setContentType("application/json");
        response.setContentLength(jsonResponse.getBytes(StandardCharsets.UTF_8).length);
        response.getWriter().write(jsonResponse);
    }
}
