package com.sdi.client.action;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import alb.util.console.Console;
import alb.util.menu.Action;

import com.sdi.business.AdminService;
import com.sdi.dto.UserTask;
import com.sdi.business.impl.RemoteEjbServicesLocator;


public class ListUsersAction implements Action {

private static final String ADMIN_SERVICE_JNDI_KEY = "TaskManager3/" + "TaskManager3EJB/" + "EjbAdminService!"
		+ "com.sdi.business.impl.admin.RemoteAdminService";
	
	public ListUsersAction() {}

	@Override
	public void execute() throws Exception {
		try {
			Context ctx = new InitialContext();
			AdminService AS = (AdminService) ctx.lookup(ADMIN_SERVICE_JNDI_KEY);
			
			List<UserTask> usuarios = AS.listAllUsers();
			
			printUser(usuarios);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	//Ojo al cast, hay que hacerlo mejor
	private void printUser(List<UserTask> user) {
		for(UserTask u: user){
			if(!u.getUser().getIsAdmin()){
		Console.println(((UserTask) user).getUser().getId() +"\t"+ ((UserTask) user).getUser().getLogin()
				 +"\t"+ ((UserTask) user).getUser().getEmail() +"\t"+ ((UserTask) user).getUser().getIsAdmin()
				 +"\t"+ ((UserTask) user).getUser().getStatus() +"\t"+ ((UserTask) user).getTasksDone()
				 +"\t"+ ((UserTask) user).getTasksDone_delay() +"\t"+ ((UserTask) user).getTasksPlanned()
				 +"\t"+ ((UserTask) user).getTasksPlanned());
			}
		}
	}

}