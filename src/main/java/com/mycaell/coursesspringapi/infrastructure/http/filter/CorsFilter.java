package com.mycaell.coursesspringapi.infrastructure.http.filter;

import com.mycaell.coursesspringapi.application.shared.configuration.properties.CorsProperties;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

    private final CorsProperties corsProperties;

    public CorsFilter(CorsProperties corsProperties) {
        this.corsProperties = corsProperties;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        httpServletResponse.setHeader("Access-Control-Allow-Origin", corsProperties.getAllowedOrigin());
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");

        if (httpServletRequest.getMethod().equals("OPTIONS")
                && httpServletRequest.getHeader("Origin").equals(corsProperties.getAllowedOrigin())) {

            httpServletResponse.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept");
            httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, PATCH, DELETE, OPTIONS");
            httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
            
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(request, response);
        }
    }

}
