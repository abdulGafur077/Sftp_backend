package com.sftp.webservice.exception;

import org.springframework.core.ErrorCoded;

public enum GeneralError implements ErrorCoded
{
	ALREADY_EXISTS(101), DEPENDENCY_EXISTS(102), UNAUTHORIZED(103), INTERNAL_ERROR(99), INVALID_PARMS(98), INVALID_USER_ID(104);

	private final int	number;

	private GeneralError(int number)
	{
		this.number = number;
	}

	public String getErrorCode()
	{
		return String.valueOf(number);
	}
}