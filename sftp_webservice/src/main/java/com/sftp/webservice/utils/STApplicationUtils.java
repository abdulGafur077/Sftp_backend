package com.sftp.webservice.utils;

import java.util.Calendar;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import com.sftp.common.consts.ApplicationConstants;
import com.sftp.dto.user.UserAuthenticationDTO;
import com.sftp.webservice.common.CommonResponse;

public class STApplicationUtils {
	public static String getAuthJSON(UserAuthenticationDTO authenticationDTO) throws JSONException {
		JSONObject authHJson = new JSONObject();
		authHJson.put(ApplicationConstants.USER_NAME, authenticationDTO.getUsername());
		return authHJson.toString();
	}

	public static Date getSessionTimeOut(String sessionTimeOut) {
		Calendar sessionTimeOutCal = Calendar.getInstance();
		sessionTimeOutCal.add(Calendar.MINUTE, Integer.parseInt(sessionTimeOut));
		return sessionTimeOutCal.getTime();
	}

	public static void setErrorResponse(CommonResponse commonResponseDTO, String errorCode, String errorMessage) {
		commonResponseDTO.setErrorcode(errorCode);
		commonResponseDTO.setInternalErrorMessage(errorMessage);
		commonResponseDTO.setStatus(ApplicationConstants.getFailure());
	}
}
