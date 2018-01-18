package com.sftp.webservice.listener;

import javax.servlet.ServletContextEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;

public class ApplicationContextLoader extends ContextLoaderListener
{
	private static final Logger OUT = LoggerFactory.getLogger(ApplicationContextLoader.class);

	@Override
	public void contextInitialized(ServletContextEvent arg0)
	{
		super.contextInitialized(arg0);
		try
		{
			OUT.info("*******************************************************");
			OUT.info("      Webservice initialized successfully              ");
			OUT.info("*******************************************************");
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}

	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0)
	{
		super.contextDestroyed(arg0);
		try
		{
			OUT.info("*******************************************************");
			OUT.info("      Webservice uninitialized successfully            ");
			OUT.info("*******************************************************");
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}

	}

}
