package com.sftp.enums;

public enum ModuleNameEnum
{
	LOGIN("Login"), LOGOUT("Logout"), CAP_DETAILS("Capabilities Details");

	private String moduleName;

	private ModuleNameEnum(String moduleName)
	{
		this.moduleName = moduleName;
	}

	public String getModuleName()
	{
		return moduleName;
	}

}
