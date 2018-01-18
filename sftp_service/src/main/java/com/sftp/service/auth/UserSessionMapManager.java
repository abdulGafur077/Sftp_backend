package com.sftp.service.auth;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sftp.dto.user.UserSessionDTO;

public class UserSessionMapManager {
	private static final Logger OUT = LoggerFactory.getLogger(UserSessionMapManager.class);
	private static final UserSessionMapManager INSTANCE = new UserSessionMapManager();
	private static Map<String, UserSessionDTO> userSessionMap = new HashMap<String, UserSessionDTO>();

	public void doUpdate(UserSessionDTO userSessionDTO) {
		OUT.debug("Update user Session details from Map , username: {}", userSessionDTO.getUsername());
		userSessionMap.put(userSessionDTO.getUsername(), userSessionDTO);
		OUT.debug("Update user Session details in cache, employee: {} successful", userSessionDTO);
	}

	public void doInsert(UserSessionDTO userSessionDTO) {
		OUT.debug("Update user Session details from Map , Username: {}", userSessionDTO.getUsername());
		userSessionMap.put(userSessionDTO.getUsername(), userSessionDTO);
		OUT.debug("Update user Session details in cache, Username: {} successful", userSessionDTO);
	}

	public void doDelete(String userLoginId) {
		OUT.debug("Remove user Session from Map , userLoginId: {}", userLoginId);
		if (userSessionMap.containsKey(userLoginId))
			userSessionMap.remove(userLoginId);
	}

	/**
	 * @param Username
	 */
	public UserSessionDTO getUserSessionByUserLoginId(String Username) {
		OUT.debug("Getting user details from Map, Username: {}", Username);
		UserSessionDTO values = null;
		values = userSessionMap.get(Username);
		return values;
	}

	/**
	 * @param Allc
	 *            userSessions
	 */
	public List<UserSessionDTO> getALLUserSessions() {
		OUT.debug("Getting all user details from cache");
		Collection<UserSessionDTO> values = null;
		values = userSessionMap.values();
		OUT.debug("Number of user auth details found: {}", values != null ? values.size() : 0);
		return (List<UserSessionDTO>) values;
	}

	public static UserSessionMapManager getInstance() {
		return INSTANCE;
	}
}
