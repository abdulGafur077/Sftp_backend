package com.sftp.enums;

public enum CustomFilterEnum
{
	DAY("Day"), WEEK("Week"), MONTH("Month"), YEAR("Year"), RANGE("Range");

	private String value;

	private CustomFilterEnum(String value)
	{
		this.value = value;
	}

	public String getValue()
	{
		return value;
	}
}
