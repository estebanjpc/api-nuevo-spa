package com.app.jwt;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.app.service.IJwtService;
import com.app.service.JwtServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter{
	
	private final IJwtService jwtService;

	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, IJwtService jwtService) {
		super(authenticationManager);
		this.jwtService = jwtService;
		
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String header = request.getHeader("Authorization");
		
		if(!requiresAuthentication(header)) {
			chain.doFilter(request, response);
			return;
		}
		
		if (!jwtService.validate(header)) {
			response.setContentType("application/json");
		    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

		    Map<String, Object> body = new LinkedHashMap<>();
		    body.put("code", "401");
		    body.put("error", "Unauthorized");
		    body.put("message", "Token inválido o expirado");
		    body.put("path", request.getRequestURI());

		    new ObjectMapper().writeValue(response.getOutputStream(), body);
		    return;
	    }
		
		UsernamePasswordAuthenticationToken authentication = null;
		if(jwtService.validate(header))
			authentication = new UsernamePasswordAuthenticationToken(jwtService.getUsername(header), null, jwtService.getRoles(header));
		
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
		
	}

	protected boolean requiresAuthentication(String header) {
		
		
		if(header == null || !header.startsWith(JwtServiceImpl.TOKEN_PREFIX)) {
			return false;
		}
		return true;
	}
	
	
}
