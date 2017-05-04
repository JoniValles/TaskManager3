package com.sdi.client.action;

import java.util.List;

import com.sdi.business.AdminService;
import com.sdi.business.exception.BusinessException;
import com.sdi.dto.User;
import com.sdi.business.impl.RemoteEjbServicesLocator;


public class DisableUserAction implements Action {

	@Override
	public void execute() throws Exception {
		AdminService AS = new RemoteServicesLocator().getAdminService();
		listarUsuarios(AS);
		Long id = Console.readLong("Deshabilitar usuario con ID: ");
		
		if(id!=null){
			AS.disableUser(id);
			Console.println("El usuario con Id="+id+" ha sido deshabilitado. ");
		}

	}

	private void listarUsuarios(AdminService AS) {
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