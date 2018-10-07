package controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import controller.Action;
import databean.CommentBean;
import databean.PostBean;
import databean.UserBean;
import formbean.PostFormBean;
import model.CommentDAO;
import model.Model;
import model.PostDAO;
import model.UserDAO;

public class DeleteAction extends Action {

	private static final long serialVersionUID = 1L;

	private UserDAO userDAO;
	private PostDAO postDAO;
	private CommentDAO commentDAO;

	public DeleteAction(Model model) {
		userDAO = model.getUserDAO();
		postDAO = model.getPostDAO();
		commentDAO = model.getCommentDAO();
	}

	@Override
	public String getName() {
		return "delete.do";
	}

	public String performPost(HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute("user");
		if (user == null) {
			// connection broken, back to login
			return "login.do";
		}

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			String postIdStr = request.getParameter("postId");
			String commentIdStr = request.getParameter("commentId");

			// check null
			if (postIdStr == null && commentIdStr == null) {
				errors.add("Not a valid delete action: missing postId and commentId");
			}

			// TODO: consider transaction more here
			if (postIdStr != null) {
				long postId = Long.parseLong(postIdStr);
				// use transaction on delete post to avoid race condition (comment on a post
				// that has gone etc.)
				Transaction.begin();
				PostBean postFromDB = postDAO.read(postId);
				if (postFromDB != null) {
					postDAO.delete(postId);
				}
				Transaction.commit();
			}

			if (commentIdStr != null) {
				long commentId = Long.parseLong(commentIdStr);
				// use transaction on delete comment to avoid race condition (comment on a post
				// that has gone etc.)
				Transaction.begin();
				CommentBean commentFromDB = commentDAO.read(commentId);
				if (commentFromDB != null) {
					commentDAO.delete(commentId);
				}
				Transaction.commit();
			}

			// put the content in text area back
			PostFormBean postForm = new PostFormBean(request);
			request.setAttribute("postForm", postForm);

			
			
			String userEmail = (String) session.getAttribute("userEmail");
			UserBean userSelected = null;
			if (userEmail != null) {
				userSelected = userDAO.read(userEmail);
				request.setAttribute("userSelected", userSelected);
			}
			
			
			// put the updated posts back
			// if userSelected is null, it's from home page
			PostBean[] posts = postDAO.getPostsFromUser((userSelected != null) ? userSelected.getEmail() : user.getEmail());
			request.setAttribute("posts", posts);

			// put the users back
			UserBean[] users = userDAO.getUsers();
			request.setAttribute("users", users);

			// map for adding comments under each related post
			Map<Long, CommentBean[]> postIdToCommentsMap = new HashMap<>();
			for (PostBean postsFromUser : posts) {
				long postIdFromUser = postsFromUser.getPostId();
				CommentBean[] commentsFromPostId = commentDAO.getCommentsFromPost(postIdFromUser);
				postIdToCommentsMap.put(postIdFromUser, commentsFromPostId);
			}
			request.setAttribute("postIdToCommentsMap", postIdToCommentsMap);

			// map email to user full name, easier for comment creation
			Map<String, String> emailToFullNameMap = new HashMap<>();
			for (UserBean userExisting : users) {
				emailToFullNameMap.put(userExisting.getEmail(),
						userExisting.getFirstName() + " " + userExisting.getLastName());
			}
			request.setAttribute("emailToFullNameMap", emailToFullNameMap);

			return getNextPage(request, errors);
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return getNextPage(request, errors);
		} catch (Exception e) {
			errors.add(e.getMessage());
			return getNextPage(request, errors);
		}
	}

	/**
	 * forward to the correct page
	 */
	private String getNextPage(HttpServletRequest request, List<String> errors) {
		if (errors.size() > 0) {
			return "action-error-message.jsp";
		}
		
		String requestURI = request.getParameter("requestURIFromPostCommentTemplate");
		if (requestURI != null && requestURI.contains("home")) {
			return "home.jsp";
		}
		if (requestURI != null && requestURI.contains("visitor")) {
			return "visitor.jsp";
		}
		return "action-error-message.jsp";
	}
	
}
