package com.sdi.dto;

import java.io.Serializable;

import com.sdi.dto.types.UserStatus;

public class UserTask implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	private User user;
	private Long id;
	private String login;
	private String password;
	private String email;
	
	private boolean isAdmin;
	private UserStatus status;
	private int tasksDone;
	private int tasksDone_delay;
	private int tasksPlanned;
	private int tasksNoPlanned;
	
	public UserTask() {
	}
	
	public UserTask(User user, int tasksDone, int tasksDone_delay,
			int tasksPlanned, int tasksNoPlanned) {
		super();
		this.id = user.getId();
		this.login = user.getLogin();
		this.password = user.getPassword();
		this.email = user.getEmail();
		this.isAdmin = user.getIsAdmin();
		this.status = user.getStatus();
		this.tasksDone = tasksDone;
		this.tasksDone_delay = tasksDone_delay;
		this.tasksPlanned = tasksPlanned;
		this.tasksNoPlanned = tasksNoPlanned;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public int gettasksDone() {
		return tasksDone;
	}
	public void setTasksDone(int tasksDone) {
		this.tasksDone = tasksDone;
	}
	public int getTasksDone_delay() {
		return tasksDone_delay;
	}
	public void setTasksDone_delay(int tasksDone_delay) {
		this.tasksDone_delay = tasksDone_delay;
	}
	public int getTasksPlanned() {
		return tasksPlanned;
	}
	public void setTasksPlanned(int tasksPlanned) {
		this.tasksPlanned = tasksPlanned;
	}
	public int getTasksNoPlanned() {
		return tasksNoPlanned;
	}
	public void setTasksNoPlanned(int tasksNoPlanned) {
		this.tasksNoPlanned = tasksNoPlanned;
	}
}