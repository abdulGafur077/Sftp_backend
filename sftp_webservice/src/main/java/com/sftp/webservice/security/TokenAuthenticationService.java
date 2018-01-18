package com.sftp.webservice.security;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

import com.sftp.dto.user.UserSessionDTO;
import com.sftp.service.auth.UserSessionMapManager;

public class TokenAuthenticationService {
	public static final String AUTH_HEADER_NAME = "x-auth-token";
	// private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";
	private final TokenHandler tokenHandler;

	/**
	 * @param secret
	 * @param userService
	 */
	public TokenAuthenticationService(UserDetailService userService) {
		tokenHandler = new TokenHandler(userService);
	}

	/**
	 * @param response
	 * @param authentication
	 * @throws UnsupportedEncodingException
	 */
	public String addAuthentication(HttpServletResponse response, UserAuthentication authentication)
			throws UnsupportedEncodingException {
		final User user = authentication.getDetails();
		String GeneratedToken = tokenHandler.createTokenForUser(user);
		response.setHeader(AUTH_HEADER_NAME, GeneratedToken);
		return GeneratedToken;
	}

	/**
	 * @param response
	 * @param authentication
	 * @param userSessionDTO
	 * @throws UnsupportedEncodingException
	 */
	public String addAuthentication(HttpServletResponse response, UserAuthentication authentication,
			UserSessionDTO userSessionDTO) {
		final User user = authentication.getDetails();
		String GeneratedToken = tokenHandler.createTokenForUser(user);
		response.setHeader(AUTH_HEADER_NAME, GeneratedToken);
		userSessionDTO.setJwtToken(GeneratedToken);
		return GeneratedToken;
	}

	/**
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public Authentication getAuthentication(HttpServletRequest request) throws Exception {
		final String token = request.getHeader(AUTH_HEADER_NAME);
		if (token != null) {
			final User user = tokenHandler.parseUserFromToken(token);
			if (user != null) {
				updateUserSessionMap(tokenHandler.parseUserLoginIdFromToken(token));
				return new UserAuthentication(user);
			}
		}
		return null;
	}

	private void updateUserSessionMap(String userLoginId) {
		UserSessionMapManager sessionMapManager = new UserSessionMapManager();
		UserSessionDTO userSessionDTO = UserSessionMapManager.getInstance().getUserSessionByUserLoginId(userLoginId);
		if (null != userSessionDTO) {
			sessionMapManager.doUpdate(userSessionDTO);
		}
	}

}
