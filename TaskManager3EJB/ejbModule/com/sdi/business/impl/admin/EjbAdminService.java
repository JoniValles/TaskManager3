package com.sdi.business.impl.admin;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.WebService;

import com.sdi.business.exception.BusinessException;
import com.sdi.business.impl.admin.command.*;
import com.sdi.business.impl.command.Command;
import com.sdi.business.impl.command.CommandExecutor;
import com.sdi.dto.User;
import com.sdi.persistence.Persistence;
/**
 * Session Bean implementation class EjbAdminService
 */
@Stateless
@LocalBean
@WebService(name="AdminService")
public class EjbAdminService implements EjbAdminServiceRemote, EjbAdminServiceLocal {

    /**
     * Default constructor. 
     */
    public EjbAdminService() {
        // TODO Auto-generated constructor stub
    }
	@Override
	public void deepDeleteUser(Long id) throws BusinessException {
		new CommandExecutor<Void>().execute( new DeepDeleteUserCommand( id ) );
	}

	@Override
	public void disableUser(Long id) throws BusinessException {
		new CommandExecutor<Void>().execute( new DisableUserCommand( id ) );
	}

	@Override
	public void enableUser(Long id) throws BusinessException {
		new CommandExecutor<Void>().execute( new EnableUserCommand( id ) );
	}


	@Override
	public User findUserById(final Long id) throws BusinessException {
		return new CommandExecutor<User>().execute( new Command<User>() {
			@Override public User execute() throws BusinessException {
				return Persistence.getUserDao().findById(id);
			}
		});
	}

	@Override
	public List<User> findAllUsers() throws BusinessException {
		return new CommandExecutor<List<User>>().execute( new Command<List<User>>() {
			@Override public List<User> execute() throws BusinessException {
				return Persistence.getUserDao().findAll();
			}
		});
	}
	@Override
	public void restartBD() throws BusinessException {
		new CommandExecutor<Void>().execute( new restartBDCommand() );
		
	}
	
	

}
