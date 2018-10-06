package dao;

import java.util.Arrays;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.RollbackException;

import databean.UserBean;

public class UserDAO extends GenericDAO<UserBean> {
	public UserDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(UserBean.class, tableName, cp);
	}

	public UserBean[] getUsers() throws RollbackException {
		// empty match to fetch all beans
		UserBean[] users = match();
		Arrays.sort(users);
		return users;
	}
}
