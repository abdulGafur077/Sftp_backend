package com.sftp.common.consts;

import com.sftp.enums.ActionsEnum;

public class ApplicationConstants {
	private static final String SEARCH_XML = "searchXML";
	public static final String EXCEPTION = "Exception";
	public static final String JSONEXCEPTION = "JSONException";
	private static final String SUCCESS = "SUCCESS";
	private static final String FAILURE = "FAILURE";
	private static final String USER_SESSION_OBJECT = "userSessionObj";

	public static final String AUTHN = "/authn";
	public static final String AUTHZ = AUTHN + "/authz";

	public static final String SCREEN = "screen";
	public static final String ADD = ActionsEnum.ActionURI.DO_ADD;
	public static final String MOD = ActionsEnum.ActionURI.DO_MODIFY;
	public static final String DELETE = ActionsEnum.ActionURI.DO_DELETE;
	public static final String VIEW = ActionsEnum.ActionURI.DO_VIEW;

	public static final String ANON = "ANON";

	// DbProperty constants
	private static final String PASSWORD = "password";
	private static final String USER = "user";
	private static final String DRIVER = "driver";
	private static final String URL = "url";

	public static final String DEVICE_XS = "xsmall";
	public static final String DEVICE_S = "small";
	public static final String DEVICE_M = "medium";
	public static final String DEVICE_L = "large";

	// Web Service Auth
	public static final String USER_NAME = "USER_NAME";

	// Application Constants
	private static final String TOKEN_TIMEOUT = "com.sftp.jwttoken.timeout.min";

	// Audit String
	private static final String AUDIT_MSG_HAS_CREATED = " has been created.";
	private static final String AUDIT_MSG_HAS_MOD = " has been modified.";
	private static final String AUDIT_MSG_HAS_DEL = " has been deleted.";

	public static String getException() {
		return EXCEPTION;
	}

	public static String getTokenTimeout() {
		return TOKEN_TIMEOUT;
	}

	public static String getFailure() {
		return FAILURE;
	}

	public static String getSuccess() {
		return SUCCESS;
	}

	public static String getAuditMsgHasCreated() {
		return AUDIT_MSG_HAS_CREATED;
	}

	public static String getAuditMsgHasMod() {
		return AUDIT_MSG_HAS_MOD;
	}

	public static String getAuditMsgHasDel() {
		return AUDIT_MSG_HAS_DEL;
	}

}
