package com.sftp.webservice.controller.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sftp.common.consts.ApplicationConstants;
import com.sftp.common.consts.URIConstants;
import com.sftp.common.exception.STException;
import com.sftp.webservice.common.CommonResponse;
import com.sftp.webservice.exception.GeneralError;
import com.sftp.webservice.security.TokenAuthenticationService;
import com.sftp.webservice.utils.STApplicationUtils;

@RestController

public class LogoutController {
	private static final Logger OUT = LoggerFactory.getLogger(LogoutController.class);

	@Autowired
	TokenAuthenticationService authService;

	@RequestMapping(value = URIConstants.LOGOUT)
	public CommonResponse logout(Authentication auth) throws Exception {
		CommonResponse commonResponse = new CommonResponse();
		OUT.info("LOGOUT: logout operation starting...");
		try {
			// UserSessionDTO userSessionDTO =
			// STApplicationUtils.getUserSessionFromAuth(auth);
			// new
			// LogoutService().doDeleteTokenFromMap(userSessionDTO.getUserLoginId());
			commonResponse.setStatus(ApplicationConstants.getSuccess());
		} catch (STException e) {
			OUT.error(ApplicationConstants.getException(), e);
			STApplicationUtils.setErrorResponse(commonResponse, e.getMessage(), e.getErrorMessage());
		} catch (Exception e) {
			OUT.error(ApplicationConstants.getException(), e);
			STApplicationUtils.setErrorResponse(commonResponse, GeneralError.INTERNAL_ERROR.getErrorCode(),
					e.getMessage());
		}
		OUT.info("LOGOUT: logout operation completed");
		return commonResponse;

	}
}
