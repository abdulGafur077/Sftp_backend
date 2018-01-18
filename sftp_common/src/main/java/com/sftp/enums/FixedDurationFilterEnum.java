package com.sftp.enums;

public enum FixedDurationFilterEnum
{
	TODAY("TODAY", "Current Day"),
	YESTERDAY("YESTERDAY", "Previous Day"),
	CURRENT_WEEK("CURRENT_WEEK", "Current Week"),
	LAST_WEEK("LAST_WEEK", "Last Week"),
	CURRENT_MONTH("CURRENT_MONTH", "Current Month"),
	LAST_MONTH("LAST_MONTH", "Last Month"),
	CURRENT_YEAR("CURRENT_YEAR", "Current Year"),
	LAST_YEAR("LAST_YEAR", "Last Year");

	private String	value;
	private String	displayName;

	private FixedDurationFilterEnum(String value, String displayName)
	{
		this.value = value;
		this.displayName = displayName;
	}

	public String getValue()
	{
		return value;
	}

	public String getDisplayName()
	{
		return displayName;
	}
}
