package com.sftp.service.userauthentication;

import java.security.GeneralSecurityException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sftp.common.consts.ApplicationConstants;
import com.sftp.dao.user.UserAuthenticationDAO;
import com.sftp.dto.user.UserAuthenticationDTO;

public class UserAuthenticationService {
	private static final Logger OUT = LoggerFactory.getLogger(UserAuthenticationService.class);

	public UserAuthenticationDTO getUserAuthByName(String username) throws Exception {
		UserAuthenticationDTO userAuthenticationDTO = null;
		try {
			userAuthenticationDTO = new UserAuthenticationDAO().getUserAuthByName(username);
		} catch (GeneralSecurityException e) {
			OUT.error(ApplicationConstants.getException(), e);
		}
		return userAuthenticationDTO;
	}

}
