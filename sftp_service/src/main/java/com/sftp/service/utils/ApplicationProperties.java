package com.sftp.service.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ApplicationProperties
{

	private static final Logger				OUT				= LoggerFactory.getLogger(ApplicationProperties.class.getName());

	private static ApplicationProperties	appProperties	= null;

	private Properties						prop;

	/**
	 * loading the properties.
	 */
	private ApplicationProperties() throws Exception
	{
		InputStream stream = null;
		try
		{
			URL url = this.getClass().getClassLoader().getResource("ApplicationProperties.properties");
			if (url == null)
			{
				OUT.warn("Could not load application properties \"ApplicationProperties.properties\" does not exist");
				return;
			}
			stream = url.openStream();
			this.prop = new Properties();
			this.prop.load(stream);
			OUT.info("Loaded application properties from " + url);
		}
		catch (IOException e)
		{
			this.prop = null;
			OUT.error("Exception", e);
		}
		finally
		{
			if (stream != null)
			{
				stream.close();
				stream = null;
			}
		}
	}

	/**
	 * @return
	 */
	public static ApplicationProperties getInstance()
	{
		try
		{
			if (null == appProperties)
			{
				appProperties = new ApplicationProperties();
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return appProperties;
	}

	/**
	 * Checks whether the property is available or not.
	 * 
	 * @param key
	 * @return
	 */
	public boolean isPropertyExist(String key)
	{
		if (null == key)
			return false;

		return !(null == this.prop.getProperty(key));
	}

	/**
	 * Retrieves the value of the key.
	 * 
	 * @param key
	 * @return
	 */
	public String getProperty(String key)
	{
		return this.prop.getProperty(key);
	}
	
	

}