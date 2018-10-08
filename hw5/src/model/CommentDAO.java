package model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import databean.CommentBean;
import databean.PostBean;

public class CommentDAO extends GenericDAO<CommentBean> {

	public CommentDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(CommentBean.class, tableName, cp);
	}

	public CommentBean[] getCommentsFromPost(long postId) throws RollbackException {
		// match the post id
		CommentBean[] comments = match(MatchArg.equals("postId", postId));
		// sort the comments
		Arrays.sort(comments);
		return comments;
	}

	/**
	 * Create a hashmap that map post id to the comments on it
	 */
	public Map<Long, CommentBean[]> getPostIdToCommentsMap(PostBean[] posts) throws RollbackException {
		Map<Long, CommentBean[]> postIdToCommentsMap = new HashMap<>();
		for (PostBean postsFromUser : posts) {
			long postIdFromUser = postsFromUser.getPostId();
			CommentBean[] commentsFromPostId = getCommentsFromPost(postIdFromUser);
			postIdToCommentsMap.put(postIdFromUser, commentsFromPostId);
		}
		return postIdToCommentsMap;
	}
}
