package com.sftp.dao.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sftp.dao.DBMappingConstants;
import com.sftp.dao.common.DBManager;
import com.sftp.dto.user.UserAuthenticationDTO;


public class UserAuthenticationDAO {
	private static final Logger OUT = LoggerFactory.getLogger(UserAuthenticationDAO.class);

	public UserAuthenticationDTO getUserAuthByName(String username) throws Exception {
		OUT.debug("Getting user authentication details by Username: {}", username);
		UserAuthenticationDTO userAuthenticationDTO = (UserAuthenticationDTO) DBManager.getInstance()
				.getResultAsObject(DBMappingConstants.GET_USER_AUTH_BY_USER_LOGINID, username);
		OUT.debug("User auth details {} for Username: {}", userAuthenticationDTO != null ? " FOUND " : " NOT FOUND",
				username);
		return userAuthenticationDTO;
	}
}
