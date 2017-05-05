package com.sdi.client.action;

import java.util.List;

import alb.util.console.Console;
import alb.util.log.Log;
import alb.util.menu.Action;

import com.sdi.business.AdminService;
import com.sdi.business.exception.BusinessException;
import com.sdi.dto.User;
import com.sdi.business.impl.RemoteEjbServicesLocator;


public class DeleteUserAction implements Action {

	@Override
	public void execute() throws Exception {
		AdminService AS = new RemoteEjbServicesLocator().getAdminService();
		listUsers(AS);
		Long id = Console.readLong("Borrar el usuario con ID:");
		
		if(id!=null){
			AS.deepDeleteUser(id);
			Console.println("El usuario con Id="+id+" ha sido borrado. ");
		}
	}
	
	private void listUsers(AdminService AS) {
		List<User> list = null;
		
		try {
			list = AS.findAllUsers();
		} catch (BusinessException e) {
			Log.warn(e.getMessage());
		}
		
		Console.println("Id\tUsuario\tEmail\tStatus");
		
		for(User u: list){
			Console.println(u.getId()+"\t"+u.getLogin()+"\t"+u.getEmail()+"\t"+
						u.getStatus());
		}
	}
}