package com.sftp.enums;

import org.springframework.core.ErrorCoded;

public enum GeneralErrorEnum implements ErrorCoded
{
	ALREADY_EXISTS(101, "Already exist"), DEPENDENCY_EXISTS(102, "Dependency exists");

	private int		number;
	private String	message;

	private GeneralErrorEnum(int number, String message)
	{
		this.number = number;
		this.message = message;
	}

	public int getNumber()
	{
		return number;
	}

	public String getMessage()
	{
		return message;
	}

	public String getErrorCode()
	{
		return String.valueOf(number);
	}
}
