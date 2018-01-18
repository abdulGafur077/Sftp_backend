package com.sftp.common.consts;

import static com.sftp.common.consts.ApplicationConstants.ADD;
import static com.sftp.common.consts.ApplicationConstants.AUTHZ;
import static com.sftp.common.consts.ApplicationConstants.DELETE;
import static com.sftp.common.consts.ApplicationConstants.MOD;
import static com.sftp.common.consts.ApplicationConstants.VIEW;

public interface URIConstants {
	String LOGIN = "/login";
	String LOGOUT = "/logout";

	// Capabilities Details
	String CAP = AUTHZ + "/cap/";
	String GET_ALL_CAP_DETAILS = CAP + "getAllCapDetails/" + VIEW;
	String GET_CAP_DETAILS = CAP + "getCapDetailsById/{id}/" + VIEW;
	String INSERT_CAP_DETAILS = CAP + "capDetails/" + ADD;
	String UPDATE_CAP_DETAILS = CAP + "capDetails/" + MOD;
	String DELETE_CAP_DETAILS_BY_ID = CAP + "capDetailsById/{id}/" + DELETE;
	
	// Role Details
	String ROLE = AUTHZ + "/role/";
	String GET_ALL_ROLE_DETAILS = ROLE + "getAllRoleDetails/" + VIEW;
	String GET_ROLE_DETAILS = ROLE + "getRoleDetailsById/{id}/" + VIEW;
	String INSERT_ROLE_DETAILS = ROLE + "roelDetails/" + ADD;
	String UPDATE_ROLE_DETAILS = ROLE + "roleDetails/" + MOD;
	String DELETE_ROLE_DETAILS_BY_ID = ROLE + "roleDetailsById/{id}/" + DELETE;
	
	//Group Details
	String GROUP = AUTHZ + "/group/";
	String GET_ALL_GROUP_DETAILS = GROUP + "getAllGroupDetails/" + VIEW;
	String GET_GROUP_DETAILS = GROUP + "getGroupDetailsById/{id}/" + VIEW;
	String INSERT_GROUP_DETAILS = GROUP + "GroupDetails/" + ADD;
	String UPDATE_GROUP_DETAILS = GROUP + "GroupDetails/" + MOD;
	String DELETE_GROUP_DETAILS_BY_ID = GROUP + "GroupDetailsById/{id}/" + DELETE;
}
