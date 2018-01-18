package com.sftp.enums;

import java.util.HashMap;
import java.util.Map;

public enum ActionsEnum
{
	VIEW("VIEW", "View", ActionURI.DO_VIEW),
	CREATE("CREATE", "Create", ActionURI.DO_ADD),
	MODIFY("MODIFY", "Modify", ActionURI.DO_MODIFY),
	DELETE("DELETE", "Delete", ActionURI.DO_DELETE),
	LOGIN("Login", "Login", ActionURI.DO_LOGIN),
	LOGOUT("Logout", "Logout", ActionURI.DO_LOGOUT);

	private String									actionName;
	private String									actionDefination;
	private String									uriAction;
	private static final Map<String, ActionsEnum>	actionNameMap	= new HashMap<String, ActionsEnum>();

	private ActionsEnum(String actionName, String actionDefination, String uriAction)
	{
		this.actionName = actionName;
		this.actionDefination = actionDefination;
		this.uriAction = uriAction;
	}

	static
	{
		for (ActionsEnum action : values())
		{
			actionNameMap.put(action.getActionName(), action);
		}
	}

	public static final class ActionURI
	{
		public static final String	DO_VIEW					= "doView";
		public static final String	DO_ADD					= "doAdd";
		public static final String	DO_MODIFY				= "doMod";
		public static final String	DO_DELETE				= "doDelete";


		public static final String	DO_LOGIN				= "doLogin";

		public static final String	DO_LOGOUT				= "doLogout";
		public static final String	DO_MODIFYLOGO			= "doModifyLogo";
	}

	public String getActionName()
	{
		return actionName;
	}

	public String getUriAction()
	{
		return uriAction;
	}

	public static Map<String, ActionsEnum> getActionnamemap()
	{
		return actionNameMap;
	}

	public String getActionDefination()
	{
		return actionDefination;
	}
}
