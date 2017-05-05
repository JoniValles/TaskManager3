package com.sdi.persistence.impl;



import com.sdi.persistence.CategoryDao;
import com.sdi.persistence.PersistenceFactory;
import com.sdi.persistence.TaskDao;
import com.sdi.persistence.UserDao;

/**
 * Implementaci??????n de la factoria que devuelve implementaci??????n de la capa
 * de persistencia con Jdbc 
 * 
 * @author alb
 *
 */
public class SimplePersistenceFactory implements PersistenceFactory {

	
	@Override
	public UserDao createUserDao() {
		return new UserDaoJdbcImpl();
	}

	@Override
	public TaskDao createTaskDao() {
		return new TaskDaoJdbcImpl();
	}

	@Override
	public CategoryDao createCategoryDao() {
		return new CategoryDaoJdbcImpl();

	}


}
