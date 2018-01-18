package com.sftp.common.exception;

public class STException extends RuntimeException
{
	private static final long	serialVersionUID	= 1L;

	private String				errorMessage;
	private String[]			errorParams;
	private String				errorCode;

	public STException(String errorCode, String errorMessage, String... errorParams)
	{
		super(errorMessage);
		this.errorMessage = errorMessage;
		this.errorParams = errorParams;
		this.errorCode = errorCode;
	}

	public String getErrorMessage()
	{
		return errorMessage;
	}

	public String[] getErrorParams()
	{
		return errorParams;
	}

	public String getErrorCode()
	{
		return errorCode;
	}

}
