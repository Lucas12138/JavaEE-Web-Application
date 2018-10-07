package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import controller.Action;
import databean.PostBean;
import databean.UserBean;
import formbean.PostFormBean;
import model.Model;
import model.PostDAO;
import model.UserDAO;

public class DeleteAction extends Action {

	private static final long serialVersionUID = 1L;

	private UserDAO userDAO;
	private PostDAO postDAO;

	public DeleteAction(Model model) {
		userDAO = model.getUserDAO();
		postDAO = model.getPostDAO();
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

			// check null
			if (postIdStr == null) {
				errors.add("null postId found");
			}

			long postId = Long.parseLong(postIdStr);

			// use transaction on delete post to avoid race condition (comment on a post
			// that has gone)
			Transaction.begin();
			postDAO.delete(postId);
			Transaction.commit();
			
			// put the content in text area back
			PostFormBean form = new PostFormBean(request);
			request.setAttribute("form", form);
			
			// put the updated posts back
			PostBean[] posts = postDAO.getPostsFromUser(user.getEmail());
			request.setAttribute("posts", posts);

			// put the users back
			UserBean[] users = userDAO.getUsers();
			request.setAttribute("users", users);
			return "home.jsp";
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "home.jsp";
		} catch (Exception e) {
			errors.add(e.getMessage());
			return "home.jsp";
		}
	}

}
