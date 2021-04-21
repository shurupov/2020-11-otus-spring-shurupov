package ru.otus.shurupov.spring.hystrix.config.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class SpaLoginResponseGeneratingFilter extends GenericFilterBean {

    private final String loginPageUrl;
    private final String logoutSuccessUrl;
    private final String failureUrl;

    public SpaLoginResponseGeneratingFilter() {
        this.loginPageUrl = "/login";
        this.failureUrl = "/login?error";
        this.logoutSuccessUrl = "/login?logout";
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        doFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }

    private void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        boolean loginError = isErrorPage(request);
        boolean logoutSuccess = isLogoutSuccess(request);
        if (isLoginUrlRequest(request) || loginError || logoutSuccess) {
            String jsonResponse = generateJsonResponse(request, loginError, logoutSuccess);
            response.setContentType("application/json");
            response.setContentLength(jsonResponse.getBytes(StandardCharsets.UTF_8).length);
            response.getWriter().write(jsonResponse);
            return;
        }
        chain.doFilter(request, response);
    }

    private String generateJsonResponse(HttpServletRequest request, boolean loginError, boolean logoutSuccess) {
        Map<String, Object> response = new HashMap<>();

        String errorMsg = "Invalid credentials";
        if (loginError) {
            response.put("status", "error");
            HttpSession session = request.getSession(false);
            if (session != null) {
                AuthenticationException ex = (AuthenticationException) session
                        .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
                errorMsg = (ex != null) ? ex.getMessage() : "Invalid credentials";
            }
            response.put("message", errorMsg);
        } else if (logoutSuccess) {
            response.put("status", "success");
            response.put("message", "You have successfully logged out!");
        } else {
            response.put("status", "error");
            response.put("message", "You are not authenticated!");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            return "{\"status\":\"error\",\"message\":\"Response serialize error!\"}";
        }
    }

    private boolean isLogoutSuccess(HttpServletRequest request) {
        return matches(request, this.logoutSuccessUrl);
    }

    private boolean isLoginUrlRequest(HttpServletRequest request) {
        return matches(request, this.loginPageUrl);
    }

    private boolean isErrorPage(HttpServletRequest request) {
        return matches(request, this.failureUrl);
    }

    private boolean matches(HttpServletRequest request, String url) {
        if (!"GET".equals(request.getMethod()) || url == null) {
            return false;
        }
        String uri = request.getRequestURI();
        int pathParamIndex = uri.indexOf(';');
        if (pathParamIndex > 0) {
            // strip everything after the first semi-colon
            uri = uri.substring(0, pathParamIndex);
        }
        if (request.getQueryString() != null) {
            uri += "?" + request.getQueryString();
        }
        if ("".equals(request.getContextPath())) {
            return uri.equals(url);
        }
        return uri.equals(request.getContextPath() + url);
    }

}
