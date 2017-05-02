 package com.sdi.presentation;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import alb.util.log.Log;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.sdi.business.AdminService;
import com.sdi.business.ServicesFactoryImpl;
import com.sdi.business.TaskService;
import com.sdi.business.UserService;
import com.sdi.business.exception.BusinessException;
import com.sdi.dto.Category;
import com.sdi.dto.Task;
import com.sdi.dto.User;
import com.sdi.dto.types.UserStatus;


public class BeanUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4346788724162425311L;
	
	//usuario de la aplicacion
	private User user = new User();
	
	private List<User> users = null;
	private List<Task> tasks = null;
	
	//informacion que usaremos para crear usuarios
	private String login;
	private String password;
	private String password2;
	private String email;
	
	@ManagedProperty(value = "#{task}")
	private BeanTask task = null;

	private String pass=" ";
		
	
	@ManagedProperty("#{category}")
	private BeanCategory category;
	
	private String categoryName;
	
	//String usado para informar al usuario
	private String msg="";




	
	public BeanUser() {
		iniciarUser(null);
	}

	public void iniciarUser(ActionEvent event) {
		user.setId(null);
		user.setLogin("login");
		user.setPassword("password");
		user.setIsAdmin(false);
		user.setEmail("email");
		user.setStatus(UserStatus.DISABLED);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User alumno) {
		this.user = alumno;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

//	public List<Task> getFinishedTask() {
//		return finishedTask;
//	}
//
//	public void setFinishedTask(List<Task> finishedTask) {
//		this.finishedTask = finishedTask;
//	}
//
//	public List<Task> getWeekTask() {
//		return weekTask;
//	}
//
//	public void setWeekTask(List<Task> weekTask) {
//		this.weekTask = weekTask;
//	}
//
//	public List<Task> getTodayTask() {
//		return todayTask;
//	}
//
//	public void setTodayTask(List<Task> todayTask) {
//		this.todayTask = todayTask;
//	}

	public BeanTask getTask() {
		return task;
	}

	public void setTask(BeanTask task) {
		this.task = task;
	}

	public BeanCategory getCategory() {
		return category;
	}

	public void setCategory(BeanCategory category) {
		this.category = category;
	}
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String validar() {
		
		try {
			
//			Map<String, Object> session = FacesContext.getCurrentInstance()
//					.getExternalContext().getSessionMap();
//			session.put("LOGGEDIN_USER", user);
			if (user.getIsAdmin()) { 
				listadoUsuarios();
				return "admin";
			}

//			listadoTareas();

		} catch (BusinessException b) {
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Error al iniciar sesion"));

		//	return "error"; 
			
		}

		return "user"; 
	}


	public String cambiarEstado(User user){
		AdminService adminService;
		if(user == null)
			return "error";
		try{
			adminService= ServicesFactoryImpl.getAdminService();
			if(user !=null && user.getStatus().equals(UserStatus.ENABLED)){
				adminService.disableUser(user.getId());
			}else{
				adminService.enableUser(user.getId());
			}
			listadoUsuarios(); 
			Log.debug("Estado del usuario [%s] cambiado con exito a [%s]",
					user.getLogin(), user.getStatus().toString());
			return "exito"; 
		}catch(BusinessException b){
			Log.error("Se ha producido un error al intentar cambiar el"
					+ "estado del usuario");
			return "error";
		}
	}

	public String eliminar(User user){
		AdminService adminService;
		if(user == null)
			return "error";
		try{
			adminService = ServicesFactoryImpl.getAdminService();
			adminService.deepDeleteUser(user.getId());
			listadoUsuarios();
			Log.debug("Se ha eliminado el usuario [%s] con exito",
					user.getLogin());
			return "exito"; 
		} catch (BusinessException b){
			Log.error("Se ha producido algun error al intentar eliminar el"
					+ "usuario");
			return "error";
		}
	}

	private void listadoUsuarios() throws BusinessException {
		users =  ServicesFactoryImpl.getAdminService().findAllUsers();
	}

//	public String listadoTareas() throws BusinessException{
////		
////			tasks = new ArrayList<Task>();
////			finishedTask=Services.getTaskService().findFinishedInboxTasksByUserId(user.getId());
////			weekTask=Services.getTaskService().findWeekTasksByUserId(user.getId());
////			todayTask=Services.getTaskService().findTodayTasksByUserId(user.getId());
////		
////			if(finishedTask!=null)
////				tasks.addAll(finishedTask);
////			if(todayTask!=null)
////				tasks.addAll(todayTask);
////			if(weekTask!=null)
////				tasks.addAll(weekTask);
//		task.mostrar
//		return "true";
//	}
////	

	
	public String registro(){
		return "true";
	}

	public String registrar() {
		UserService userService;

		
		//HABRIA QUE PONER AVISOS EN EL FRONTEND
		//Comprobar email
		if (!user.getEmail().matches("[-\\w\\.]+@\\w+\\.\\w+")) {
			Log.error("Email invalido: [%s]", user.getEmail());
			return "false";
		}
		
		//Comprobar contraseña
		if (!pass.equals(user.getPassword())) {	
			Log.error("Las contraseñas no coinciden: [%s] -- [%s]", pass,
					user.getPassword());
			return "false";
		}
		//Comprobar longitud de contraseña
		else {
			if (pass.length() < 8) {
				Log.error("Las contraseñas deben tener una "
						+ "longitud de al menos 8 caracteres [%s]", pass);
				return "false";
			}
		}
		try {
			userService = ServicesFactoryImpl.getUserService();
			user.setIsAdmin(false);
			user.setStatus(UserStatus.ENABLED);
			userService.registerUser(user);

		} catch (BusinessException b) {
			return "error"; 
		}
		return "true"; 
	}

	public String actualizarEmail(){
		UserService userService;
		try {
			userService = ServicesFactoryImpl.getUserService();
			if(user.getEmail()!=null)
				userService.updateUserDetails(user);
			
		} catch (BusinessException e) {
			e.printStackTrace();
			return "error";
			
		}
		return "true";
	}
	
	public String irACrearTarea(){
		task=null;
		
		return "crearTarea";
	}
	
	public String irACrearCategoria(){
		return "crearCategoria";
	}
	/**Boton atrás
	 * 
	 * @return
	 */
	public String atras() {
		System.out.println("Atrás.");
		return "true";
	}
	
	
	public String crearCategoria() {
		TaskService taskService;
		Category cat = new Category();
		cat.setName(categoryName);
		cat.setUserId(user.getId());
		try {
			taskService = ServicesFactoryImpl.getTaskService();
			taskService.createCategory(cat);
			
		} catch (BusinessException b) {
			mostrarError(b.getMessage());
			return "err"; 
		}
		return "true";
	}
	
	public List<Category> getCategories(){
		TaskService taskService;
		taskService = ServicesFactoryImpl.getTaskService ();
		List<Category> cat=null;
		try {
			
			cat = taskService.findCategoriesByUserId(user.getId());
			
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return cat;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String listadoCategorias(){
		return "true";
		
	}
	public String irACasa(){
		return "home";
	}

	
	public Date getHoy(){
		java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		return date;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String cerrarSesion() {
		Map<String, Object> session = FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap();
		session.put("LOGGEDIN_USER", null);
		Log.info("Sesion cerrada.");

		return "true";
	}
	
	public String crearUsuario(){
		UserService uService;
		uService = ServicesFactoryImpl.getUserService();
		try {
		if(password.equals(password2)){
			User newUser = new User();
			newUser.setEmail(email);
			newUser.setLogin(login);
			newUser.setPassword(password);
		
			uService.registerUser(newUser);
			Log.info("Creando Usuario" );
			return "true";
		}else{
			Log.info("Las contraseñas han de coincidir" );
			email="";
			login="";
			password="";
			password2="";
			mostrarError("form_contraseñasDiferentes");
			return "";
		}
		
		} catch (BusinessException e) {
			mostrarError(e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "err";
		}
		
	}
	
	public void mostrarError(String msg){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		try{
        ResourceBundle bundle = facesContext.getApplication().getResourceBundle(facesContext, "msg");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", 
      		  bundle.getString(msg)));
		}catch(Exception e){
			 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", 
		      		  msg));
		}
		

	}



	

}