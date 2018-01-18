package com.sftp.webservice.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.sftp.common.consts.ApplicationConstants;
import com.sftp.common.exception.STException;
import com.sftp.dto.user.UserAuthenticationDTO;
import com.sftp.dto.user.UserSessionDTO;
import com.sftp.service.userauthentication.UserAuthenticationService;
import com.sftp.webservice.exception.GeneralError;
import com.sftp.webservice.params.LoginObject;
import com.sftp.webservice.utils.STApplicationUtils;

public class LoginService {
	private static final Logger OUT = LoggerFactory.getLogger(LoginService.class);

	public User login(LoginObject loginObject, UserSessionDTO userSessionDTO) throws Exception {
		if (loginObject.getUsername() == null || loginObject.getUsername().trim().isEmpty()
				|| loginObject.getPassword() == null || loginObject.getPassword().trim().isEmpty()) {
			throw new STException(GeneralError.INVALID_PARMS.getErrorCode(), GeneralError.INVALID_PARMS.name());
		}
		UserAuthenticationDTO userAuthenticationDTO = new UserAuthenticationService()
				.getUserAuthByName(loginObject.getUsername());
		if (userAuthenticationDTO == null) {
			throw new STException(GeneralError.UNAUTHORIZED.getErrorCode(), GeneralError.UNAUTHORIZED.name());
		}

		User user = null;
		try {
			// TODO Need to Add Encryption/Description Mechanism
			String pass = userAuthenticationDTO.getPassword();
			if (pass.equals(loginObject.getPassword())) {
				List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
				user = new User(STApplicationUtils.getAuthJSON(userAuthenticationDTO), loginObject.getUsername(),
						true, true, true, true, authorities);
				userSessionDTO.setUsername(userAuthenticationDTO.getUsername());
			} else {
				OUT.warn("Invalid Credentails is passed for Username {}", loginObject.getUsername());
				throw new STException(GeneralError.UNAUTHORIZED.getErrorCode(), GeneralError.UNAUTHORIZED.name());
			}
		} catch (Exception e) {
			OUT.error(ApplicationConstants.getException(), e);
			throw new STException(GeneralError.UNAUTHORIZED.getErrorCode(), GeneralError.UNAUTHORIZED.name());
		}
		return user;
	}
}
