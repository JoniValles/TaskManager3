package com.sdi.business.impl.admin.command;


import java.util.ArrayList;
import java.util.List;

import alb.util.date.DateUtil;

import com.sdi.business.exception.BusinessException;
import com.sdi.business.impl.command.Command;
import com.sdi.dto.Category;
import com.sdi.dto.Task;
import com.sdi.dto.User;
import com.sdi.dto.UserTask;
import com.sdi.dto.types.UserStatus;
import com.sdi.infrastructure.Factories;
import com.sdi.persistence.CategoryDao;
import com.sdi.persistence.Persistence;
import com.sdi.persistence.TaskDao;
import com.sdi.persistence.UserDao;

public class ListAllUsersCommand implements Command<List<UserTask>>{
	

	@Override
	public List<UserTask> execute() throws BusinessException {		
		List<User> usuarios = Factories.persistence.createUserDao().findAll(); //Ojo al createUser
				
		List<UserTask> info = new ArrayList<UserTask>();
		
		
		
		for(User u: usuarios) {
			List<Integer> numeros = Factories.persistence.createTaskDao().numberOfTasks(u);
			
			UserTask userTask = new UserTask();
			userTask.setTasksDone(numeros.get(0));
			userTask.setTasksDone_delay(numeros.get(1));
			userTask.setTasksPlanned(numeros.get(2));
			userTask.setTasksNoPlanned(numeros.get(3));
			userTask.setUser(u);
			
			info.add(userTask);			
		}
		
		return info;
	}

	

}
