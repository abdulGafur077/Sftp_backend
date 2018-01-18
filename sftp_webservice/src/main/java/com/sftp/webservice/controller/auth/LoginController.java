package com.sftp.webservice.controller.auth;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sftp.common.consts.ApplicationConstants;
import com.sftp.common.consts.URIConstants;
import com.sftp.common.exception.STException;
import com.sftp.dto.user.UserSessionDTO;
import com.sftp.service.auth.UserSessionMapManager;
import com.sftp.webservice.common.CommonResponse;
import com.sftp.webservice.exception.GeneralError;
import com.sftp.webservice.params.LoginObject;
import com.sftp.webservice.security.LoginService;
import com.sftp.webservice.security.TokenAuthenticationService;
import com.sftp.webservice.security.UserAuthentication;
import com.sftp.webservice.utils.STApplicationUtils;

@RestController
@RequestMapping(value = URIConstants.LOGIN)
public class LoginController {
	private static final Logger OUT = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	TokenAuthenticationService authService;

	@RequestMapping(method = RequestMethod.POST)
	public CommonResponse login(HttpServletResponse response, @RequestBody(required = true) LoginObject loginParams)
			throws Exception {
		CommonResponse commonResponseDTO = null;
		UserSessionDTO userSessionDTO = new UserSessionDTO();
		OUT.info("REQ: for Login for username : {}", loginParams.getUsername());
		try {
			commonResponseDTO = new CommonResponse();
			User user = new LoginService().login(loginParams, userSessionDTO);
			authService.addAuthentication(response, new UserAuthentication(user), userSessionDTO);
			UserSessionMapManager.getInstance().doInsert(userSessionDTO);
			commonResponseDTO.setStatus(ApplicationConstants.getSuccess());
		} catch (STException e) {
			OUT.error(ApplicationConstants.getException(), e);
			STApplicationUtils.setErrorResponse(commonResponseDTO, e.getErrorCode(), e.getErrorMessage());
		} catch (Exception e) {
			OUT.error(ApplicationConstants.getException(), e);
			STApplicationUtils.setErrorResponse(commonResponseDTO, GeneralError.INTERNAL_ERROR.getErrorCode(),
					e.getMessage());
		}
		OUT.info("RESP: for Login for username {} completed with status: {}", loginParams.getUsername(),
				commonResponseDTO.getStatus());
		return commonResponseDTO;
	}

}