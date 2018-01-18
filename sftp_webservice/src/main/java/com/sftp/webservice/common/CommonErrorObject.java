package com.sftp.webservice.common;

public class CommonErrorObject
{
	private String	errorCode;
	private String	errorField;
	private String	errorMessage;

	public String getErrorCode()
	{
		return errorCode;
	}

	public void setErrorCode(String errorCode)
	{
		this.errorCode = errorCode;
	}

	public String getErrorField()
	{
		return errorField;
	}

	public void setErrorField(String errorField)
	{
		this.errorField = errorField;
	}

	public String getErrorMessage()
	{
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage)
	{
		this.errorMessage = errorMessage;
	}

}
