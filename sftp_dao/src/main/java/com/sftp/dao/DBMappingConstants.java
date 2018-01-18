package com.sftp.dao;

public interface DBMappingConstants {
	// User Login
	String GET_USER_AUTH_BY_USER_LOGINID = "UserAuthentication.getUserAuthenticationByUserLoginId";

	// Capabilities Details
	String GET_ALL_CAP_DETAILS = "CapabilitiesDetails.GetAllCapDetails";
	String GET_CAP_DETAILS_BY_ID = "CapabilitiesDetails.GetCapDetailsById";
	String IS_CAP_DETAILS_EXIST = "CapabilitiesDetails.IsCapDetailExit";
	String INSERT_CAP_DETAILS = "CapabilitiesDetails.insertCapDetails";
	String UPDATE_CAP_DETAILS = "CapabilitiesDetails.updateCapDetails";
	String DELETE_CAP_DETAILS_BY_ID = "CapabilitiesDetails.deleteCapDetailsById";
	String GET_ALL_CAP_DETAILS_BY_ROLE_ID = "CapabilitiesDetails.getCapDetailsByRoleID";
	String INSERT_CAP_DETAILS_TO_MAPPING = "CapabilitiesDetails.insertCapDetailsToMapping";
	String DELETE_CAP_DETAILS_FROM_MAPPING = "CapabilitiesDetails.deleteCapDetailsFromMapping";

	// Role Details
	String GET_ALL_ROLE_DETAILS = "roleDetails.GetAllRoleDetails";
	String GET_ROLE_DETAILS_BY_ID = "roleDetails.GetRoleDetailsById";
	String IS_ROLE_DETAILS_EXIST = "roleDetails.IsRoleDetailsExist";
	String INSERT_ROLE_DETAILS = "roleDetails.insertRoleDetails";
	String UPDATE_ROLE_DETAILS = "roleDetails.updateRoleDetails";
	String DELETE_ROLE_DETAILS_BY_ID = "roleDetails.deleteRoleDetailsById";
	String GET_ALL_ROLE_DETAILS_BY_ROLE_ID = "roleDetails.getRoleDetailsByRoleID";
	String INSERT_ROLE_DETAILS_TO_MAPPING = "roleDetails.insertRoleDetailsToMapping";
	String DELETE_ROLE_DETAILS_FROM_MAPPING = "roleDetails.deleteRoleDetailsFromMapping";

	// Group Details
	String GET_ALL_GROUP_DETAILS = "GroupDetails.GetAllGroupDetails";
	String GET_GROUP_DETAILS_BY_ID = "GroupDetails.GetGroupDetailsById";
	String IS_GROUP_DETAILS_EXIST = "GroupDetails.IsGroupDetailsExist";
	String INSERT_GROUP_DETAILS = "GroupDetails.insertGroupDetails";
	String UPDATE_GROUP_DETAILS = "GroupDetails.updateGroupDetails";
	String DELETE_GROUP_DETAILS_BY_ID = "GroupDetails.deleteGroupDetailsById";

	// Audit Trail
	String INSERT_AUDIT_TRAIL = "AuditTrail.insertAuditTrail";
	String GET_AUDIT_DETAILS = "AuditTrail.getAuditDetails";
}
