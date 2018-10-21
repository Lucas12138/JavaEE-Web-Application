package model;

import java.util.*;
import java.util.stream.Collectors;

import databean.UserBean;
import formbean.CommentFormBean;
import org.genericdao.*;

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

	/**
	 * Use transaction to avoid adding a comment on a non-existing post
	 * @param postIdStr
	 * @param commentForm
	 * @param user
	 * @param postDAO
	 * @param errors
	 */
	public void createCommentOnPost(String postIdStr, CommentFormBean commentForm, UserBean user, PostDAO postDAO, List<String> errors) {
		try {
			Transaction.begin();

			errors.addAll(commentForm.getValidationErrors());
			if (postIdStr == null) {
				errors.add("The post you want to comment on is somehow missing");
			}

			// make sure the post exist (transaction is very important here)
			long postId = Long.parseLong(postIdStr);
			PostBean postBean = postDAO.read(postId);
			if (postBean == null) {
				errors.add("You're commenting on a post that doesn't exist");
			}

			// do this only if no errors
			if (errors.size() == 0) {
				// create new comment in db
				CommentBean commentBean = new CommentBean();
				commentBean.setCommentDatetime(new Date());
				commentBean.setContent(commentForm.getCommentContent());
				commentBean.setEmail(user.getEmail());
				commentBean.setPostId(postId);
				create(commentBean);
			}
		} catch (RollbackException e) {
			errors.add(e.toString());
		} catch (Exception e) {
			errors.add(e.toString());
		} finally {
			if (Transaction.isActive()) {
				Transaction.rollback();
			}
		}

	}
}
