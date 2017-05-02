package com.sdi.business.impl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.sdi.business.*;
public class RemoteEjbServicesLocator implements ServicesFactory {
	
	private static final String USER_SERVICE_JNDI_KEY = "TaskManager3/" + "TaskManager3EJB/" + "EjbUserService!"
			+ "com.sdi.business.impl.user.RemoteUserService";
	private static final String TASK_SERVICE_JNDI_KEY = "TaskManager3/" + "TaskManager3EJB/" + "EjbTaskService!"
			+ "com.sdi.business.impl.task.RemoteTaskService";
	private static final String ADMIN_SERVICE_JNDI_KEY = "TaskManager3/" + "TaskManager3EJB/" + "EjbAdminService!"
			+ "com.sdi.business.impl.admin.RemoteAdminService";

	@Override
	public UserService getUserService() {
		try {
			Context ctx = new InitialContext();
			return (UserService) ctx.lookup(USER_SERVICE_JNDI_KEY);
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}
	

	@Override
	public AdminService getAdminService() {
		try {
			Context ctx = new InitialContext();
			return (AdminService) ctx.lookup(ADMIN_SERVICE_JNDI_KEY);
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}

	@Override
	public TaskService getTaskService() {
		try {
			Context ctx = new InitialContext();
			return (TaskService) ctx.lookup(TASK_SERVICE_JNDI_KEY);
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}
}