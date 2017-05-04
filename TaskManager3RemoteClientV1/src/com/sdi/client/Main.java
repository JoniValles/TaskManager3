package com.sdi.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.sdi.business.AdminService;
import com.sdi.business.exception.BusinessException;
import com.sdi.dto.User;

public class Main {

	
	private static final String ADMIN_SERVICE_JNDI_KEY = "TaskManager3/"
			+ "TaskManager3EJB/" + "EjbAdminService!"
			+ "com.sdi.business.impl.admin.RemoteAdminService";

	public void main(String[] args) throws Exception {
		mostrarComandosDisponibles();
	}

	private static void mostrarUsuarios() throws Exception {
		Context ctx = new InitialContext();
		AdminService aService = (AdminService) ctx
				.lookup(ADMIN_SERVICE_JNDI_KEY);
		List<User> users = aService.findAllUsers();
		for (User u : users) {
			System.out.println("[id=" + u.getId() + "]" + " " + u.getLogin());
		}
	}

	private static void deshabilitar() throws NumberFormatException,
			IOException, BusinessException, NamingException {
		System.out.print("\nIntroducir id del usuario: ");
		Context ctx = new InitialContext();
		AdminService aService = (AdminService) ctx
				.lookup(ADMIN_SERVICE_JNDI_KEY);
		String id;
		id = leerLinea();
		aService.disableUser(Long.parseLong(id));
	}

	private static void eliminar() throws NumberFormatException,
			IOException, BusinessException, NamingException {
		System.out.print("\nIntroducir id del usuario: ");
		Context ctx = new InitialContext();
		AdminService aService = (AdminService) ctx
				.lookup(ADMIN_SERVICE_JNDI_KEY);
		String id;
		id = leerLinea();
		aService.deepDeleteUser(Long.parseLong(id));
	}

	private static void mostrarComandosDisponibles() throws Exception {
		boolean ejecucion = true;
		System.out
				.println("\n -------------------------------------------- \n");
		while (ejecucion) {
			System.out.println("\nIntroduzca el numero de la opcion deseada:");
			System.out.println();
			System.out.println("1 -> Mostrar la lista de usuarios.");
			System.out.println("2 -> Deshabilitar usuario.");
			System.out.println("3 -> Eliminar usuario.");
			System.out.println("4 -> Salir ");
			System.out.print("\nOpcion Nº: ");
			int lectura;
			lectura = Integer.parseInt(leerLinea());
			if (lectura > 4 && lectura < 1)
				;
			else if (lectura == 4)
				ejecucion = false;
			else if (lectura == 1)
				mostrarUsuarios();
			else if (lectura == 2)
				deshabilitar();
			else if (lectura == 3)
				eliminar();

		}

	}

	private static String leerLinea() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String lectura = br.readLine();
		return lectura;
	}

}
