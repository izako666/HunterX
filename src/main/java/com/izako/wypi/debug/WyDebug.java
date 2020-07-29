package com.izako.wypi.debug;

import java.lang.management.ManagementFactory;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.izako.wypi.APIConfig;

public class WyDebug
{
	private static Logger logger = Logger.getLogger(APIConfig.PROJECT_ID);
	
	public static boolean isDebug()
	{
		return ManagementFactory.getRuntimeMXBean().getInputArguments().toString().indexOf("-agentlib:jdwp") > 0;
	}
	
	public static void debug(Object msg)
	{
		if(isDebug())
			logger.log(Level.INFO, "[WYPI] [" + APIConfig.PROJECT_ID.toUpperCase() + "] " + String.valueOf(msg));
	}
}
