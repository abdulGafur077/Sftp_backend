package com.sftp.webservice.security;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sftp.common.consts.ApplicationConstants;

public class UserDetailService implements org.springframework.security.core.userdetails.UserDetailsService {
	private static final Logger OUT = LoggerFactory.getLogger(UserDetailService.class);

	private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();

	public User loadUserByUsername(String authJsonStr) throws UsernameNotFoundException {
		JSONObject authJson;
		User user = null;
		try {
			authJson = new JSONObject(authJsonStr);
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			user = new User(authJsonStr, new String(), true, true, true, true, authorities);
			OUT.debug("User Details fetched for employeeId:{}, from db:{}",
					authJson.getString(ApplicationConstants.USER_NAME), user);
			detailsChecker.check(user);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return user;

	}

	public String getLoginIdByUsername(String authJsonStr) throws JSONException {

		JSONObject authJson = new JSONObject(authJsonStr);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		User user = new User(authJsonStr, new String(), true, true, true, true, authorities);
		OUT.debug("User Details fetched for employeeId:{}, from db:{}",
				authJson.getString(ApplicationConstants.USER_NAME), user);
		// detailsChecker.check(user);
		return authJson.getString(ApplicationConstants.USER_NAME);
	}
}
