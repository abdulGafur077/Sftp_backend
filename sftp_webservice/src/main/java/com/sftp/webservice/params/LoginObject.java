package com.sftp.webservice.params;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class LoginObject
{
	@NotEmpty
	private String	username;

	@NotEmpty(message = "password should not be empty.")
	@Size(min = 6, message = "password length should be 4 Characters.")
	private String	password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

}
