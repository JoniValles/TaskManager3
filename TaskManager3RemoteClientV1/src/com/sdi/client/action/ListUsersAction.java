package com.sdi.client.action;

import java.util.List;

import com.sdi.business.AdminService;
import com.sdi.dto.UserTask;
import com.sdi.business.impl.RemoteEjbServicesLocator;


public class ListUsersAction implements Action {

	@Override
	public void execute() throws Exception {
		AdminService AS = new RemoteEjbServicesLocator().getAdminService();
		List<UserTask> lista = AS.findAllUserWithTasks();
		
		Console.println("Id\tLogin\tEmail\tIsAdmin\tStatus\tTCompletadas"
				+ "\tTCompletadas retrasadas\tTPlanificadas\tTNo planificadas");
		for(UserTask u: lista){
			printUser(u);
		}
	}

	private void printUser(UserTask u) {
		Console.println(u.getUser().getId() +"\t"+ u.getUser().getLogin()
				 +"\t"+ u.getUser().getEmail() +"\t"+ u.getUser().getIsAdmin()
				 +"\t"+ u.getUser().getStatus() +"\t"+ u.getTareasCompletadas()
				 +"\t"+ u.getTareasCompletadasRet() +"\t"+ u.getTareasPlanificadas()
				 +"\t"+ u.getTareasNoPlanificadas());
	}

}