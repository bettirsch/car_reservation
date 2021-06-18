package com.example.jedi.util;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class RequestLoggingFilter extends OncePerRequestFilter{

	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(RequestLoggingFilter.class);
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		LOGGER.info("Request URI is: {}", request.getRequestURI());
		filterChain.doFilter(request, response);
		LOGGER.info("Response Status Code is: {}", response.getStatus());
	}

}
