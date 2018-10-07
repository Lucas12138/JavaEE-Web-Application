package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.RollbackException;

import databean.CommentBean;
import databean.PostBean;
import databean.UserBean;
import formbean.CommentFormBean;
import model.CommentDAO;
import model.Model;
import model.PostDAO;
import model.UserDAO;

public class VisitorAction extends Action {

	private static final long serialVersionUID = 1L;

	private UserDAO userDAO;
	private PostDAO postDAO;
	private CommentDAO commentDAO;

	public VisitorAction(Model model) {
		userDAO = model.getUserDAO();
		postDAO = model.getPostDAO();
		commentDAO = model.getCommentDAO();
	}

	@Override
	public String getName() {
		return "visitor.do";
	}

	@Override
	public String performGet(HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute("user");
		
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			String userEmail = (String) session.getAttribute("userEmail");
			UserBean userSelected = null;
			if (userEmail != null) {
				userSelected = userDAO.read(userEmail);
				request.setAttribute("userSelected", userSelected);
			}

			// check if there's an incoming new comment request
			CommentFormBean commentForm = new CommentFormBean(request);

			if (user != null && commentForm.isNewComment()) {
				errors.addAll(commentForm.getValidationErrors());
				String postIdStr = request.getParameter("postId");
				if (postIdStr == null) {
					errors.add("The post you want to comment on is somehow missing");
				}
				long postId = Long.parseLong(postIdStr);

				// do this only if no errors
				if (errors.size() == 0) {
					// create new comment in db
					CommentBean commentBean = new CommentBean();
					commentBean.setCommentDatetime(new Date());
					commentBean.setContent(commentForm.getCommentContent());
					commentBean.setEmail(user.getEmail());
					commentBean.setPostId(postId);
					commentDAO.create(commentBean);
				}
			}

			// show the posts of the user selected
			PostBean[] posts = postDAO.getPostsFromUser(userSelected.getEmail());
			request.setAttribute("posts", posts);

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

			return "visitor.jsp";
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "visitor.jsp";
		} catch (Exception e) {
			errors.add(e.getMessage());
			return "visitor.jsp";
		}
	}

	@Override
	public String performPost(HttpServletRequest request) {
		return performGet(request);
	}
}