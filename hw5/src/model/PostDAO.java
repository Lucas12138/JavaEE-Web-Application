package model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.RollbackException;

import databean.PostBean;

public class PostDAO extends GenericDAO<PostBean> {
	public PostDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(PostBean.class, tableName, cp);
	}

	public PostBean[] getPostsFromUser(String email) throws RollbackException {
		// empty match to fetch all beans
		PostBean[] posts = match();
		
		// filter only posts of the specific user
		List<PostBean> postsFiltered = Arrays.stream(posts).filter(post -> post.getEmail().equals(email)).collect(Collectors.toList());
		
		// sort based on post datetime
		PostBean[] postsFilteredSorted = new PostBean[postsFiltered.size()];
		postsFilteredSorted = postsFiltered.toArray(postsFilteredSorted);
		Arrays.sort(postsFilteredSorted);
		
		return postsFilteredSorted;
	}
}
