package com.sftp.webservice.security;

import org.json.JSONException;
import org.springframework.security.core.userdetails.User;

import com.sftp.common.consts.ApplicationConstants;
import com.sftp.service.utils.ApplicationProperties;
import com.sftp.webservice.utils.STApplicationUtils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenHandler {
	private final String secret;
	private final UserDetailService userService;

	public TokenHandler(UserDetailService userService) {
		this.secret = "0123456789";// SecurityTokenCacheClientImpl.getInstance().getSecretKey();
		this.userService = userService;
	}

	/**
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public User parseUserFromToken(String token) {
		String username = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
		return userService.loadUserByUsername(username);
	}

	public String createTokenForUser(User user) {
		String sessionTimeOut = ApplicationProperties.getInstance().getProperty(ApplicationConstants.getTokenTimeout());
		return Jwts.builder().setSubject(user.getUsername())
				.setExpiration(STApplicationUtils.getSessionTimeOut(sessionTimeOut))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	public String parseUserLoginIdFromToken(String token) throws JSONException {
		String username = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
		return userService.getLoginIdByUsername(username);
	}
}
