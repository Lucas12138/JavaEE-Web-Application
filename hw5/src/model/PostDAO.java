package model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import databean.PostBean;

public class PostDAO extends GenericDAO<PostBean> {
	public PostDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(PostBean.class, tableName, cp);
	}

	public PostBean[] getPostsFromUser(String email) throws RollbackException {
		// match the email
		PostBean[] posts = match(MatchArg.equals("email", email));
		// sort the posts
		Arrays.sort(posts);
		return posts;
	}
}
