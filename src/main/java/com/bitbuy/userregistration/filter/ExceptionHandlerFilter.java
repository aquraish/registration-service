package com.bitbuy.userregistration.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bitbuy.userregistration.exception.ApiError;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.JwtException;

@Component
public class ExceptionHandlerFilter extends OncePerRequestFilter {

	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			filterChain.doFilter(request, response);
		} catch (JwtException e) {
			setErrorResponse(HttpStatus.UNAUTHORIZED, response, e);
		} catch (RuntimeException e) {
			setErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, response, e);
		}
	}

	public void setErrorResponse(HttpStatus status, HttpServletResponse response, Throwable ex)
			throws JsonProcessingException, IOException {
		response.setStatus(status.value());
		response.setContentType("application/json");
		ApiError apiError = new ApiError(ex.getMessage());
		response.getWriter().write(objectMapper.writeValueAsString(apiError));
	}

}
