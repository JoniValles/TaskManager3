package com.sdi.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import com.sdi.business.AdminService;
import com.sdi.dto.User;

public class Main {

//	private static final String USER_SERVICE_JNDI_KEY = "TaskManager3/" + "TaskManager3EJB/" + "EjbUserService!"
//			+ "com.sdi.business.impl.user.RemoteUserService";
//	private static final String TASK_SERVICE_JNDI_KEY = "TaskManager3/" + "TaskManager3EJB/" + "EjbTaskService!"
//			+ "com.sdi.business.impl.task.RemoteTaskService";
	private static final String ADMIN_SERVICE_JNDI_KEY = "TaskManager3/" + "TaskManager3EJB/" + "EjbAdminService!"
			+ "com.sdi.business.impl.admin.RemoteAdminService";

	 public void main(String[] args) throws Exception {
		 mostrarComandosDisponibles();
	 }
	 
	private static void mostrarUsuarios() throws Exception {
		Context ctx = new InitialContext();
		AdminService aService = (AdminService) ctx
				.lookup(ADMIN_SERVICE_JNDI_KEY);
		List<User> users = aService.findAllUsers();
		
		for (User a : users) {
			System.out.println(a.getLogin());
		}
	}
	


	private static void mostrarComandosDisponibles() throws Exception {
		boolean ejecucion=true;
		System.out
				.println("\n -------------------------------------------- \n");
		while(ejecucion){
			System.out
					.println("\nIntroduzca el numero de la opcion deseada:");
			System.out.println();
			System.out.println("1 -> Mostrar la lista de usuarios.");
			System.out.println("2 -> Deshabilitar usuario.");
			System.out.println("3 -> Eliminar usuario.");
			System.out.println("4 -> Salir ");
			System.out.print("\nOpcion Nº: ");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			int lectura;
			try {
				lectura = Integer.parseInt(br.readLine());
				if(lectura>4 && lectura<1);
				else if(lectura==4) 
					ejecucion=false;
				else if(lectura==1)
					mostrarUsuarios();
				
			} catch (IOException e) {
				
			}
		}
	}


}
