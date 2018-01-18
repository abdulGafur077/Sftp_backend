package com.sftp.webservice.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.sftp.common.consts.ApplicationConstants;

import io.jsonwebtoken.ExpiredJwtException;

public class StatelessAuthenticationFilter implements Filter {
	private static final Logger OUT = LoggerFactory.getLogger(StatelessAuthenticationFilter.class);
	private final TokenAuthenticationService authenticationService;

	public StatelessAuthenticationFilter(TokenAuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// DO nothing
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		try {
			setCorsHeader(httpResponse);
			Authentication authentication = authenticationService.getAuthentication(httpRequest);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			authenticationService.addAuthentication(httpResponse, (UserAuthentication) authentication);
			chain.doFilter(request, response);
		} catch (ExpiredJwtException e) {
			httpResponse.setStatus(HttpServletResponse.SC_REQUEST_TIMEOUT);
			OUT.error(ApplicationConstants.getException(), e);
		} catch (Exception e) {
			httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			OUT.error(ApplicationConstants.getException(), e);
		}
		SecurityContextHolder.getContext().setAuthentication(null);
	}

	/**
	 * @param httpResponse
	 */
	private void setCorsHeader(HttpServletResponse httpResponse) {
		httpResponse.setHeader("Access-Control-Allow-Headers", "x-auth-token");
		httpResponse.setHeader("Access-Control-Allow-Methods", "*");
		httpResponse.setHeader("Access-Control-Allow-Origin", "*");
		httpResponse.setHeader("Access-Control-Expose-Headers", "x-auth-token");
		httpResponse.setHeader("Access-Control-Max-Age", "1600");
		httpResponse.setHeader("Allow", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
	}

	public void destroy() {
		// DO nothing
	}

}
