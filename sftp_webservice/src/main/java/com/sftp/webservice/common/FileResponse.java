package com.sftp.webservice.common;

import java.io.Serializable;

public class FileResponse implements Serializable
{

	private static final long	serialVersionUID	= 1L;

	private String				fileName;
	private String				fileType;
	private byte[]				fileData;
	private String				fileDataStr;

	public String getFileDataStr()
	{
		return fileDataStr;
	}

	public void setFileDataStr(String fileDataStr)
	{
		this.fileDataStr = fileDataStr;
	}

	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	public String getFileType()
	{
		return fileType;
	}

	public void setFileType(String fileType)
	{
		this.fileType = fileType;
	}

	public byte[] getFileData()
	{
		return fileData;
	}

	public void setFileData(byte[] fileData)
	{
		this.fileData = fileData;
	}
}
