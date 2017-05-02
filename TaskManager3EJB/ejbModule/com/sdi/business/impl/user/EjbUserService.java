package com.sdi.business.impl.user;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;





import com.sdi.business.exception.BusinessException;
import com.sdi.business.impl.command.CommandExecutor;
import com.sdi.business.impl.user.command.FindLoggableUSerCommand;
import com.sdi.business.impl.user.command.RegisterUserCommand;
import com.sdi.business.impl.user.command.UpdateUserDetailsCommand;
import com.sdi.dto.User;

/**
 * Session Bean implementation class EjbUserService
 */
@Stateless
@LocalBean
public class EjbUserService implements EjbUserServiceRemote, EjbUserServiceLocal {

    /**
     * Default constructor. 
     */
    public EjbUserService() {
        // TODO Auto-generated constructor stub
    }
    @Override
	public Long registerUser(User user) throws BusinessException {
		return new CommandExecutor<Long>().execute( 
				new RegisterUserCommand( user ) 
		);
	}

	@Override
	public void updateUserDetails(User user) throws BusinessException {
		new CommandExecutor<Void>().execute( 
				new UpdateUserDetailsCommand( user ) 
		);
	}

	@Override
	public User findLoggableUser(final String login, final String password) 
			throws BusinessException {
		
		return new CommandExecutor<User>().execute( 
				new FindLoggableUSerCommand<User>(login, password) 
		);
	}

}
