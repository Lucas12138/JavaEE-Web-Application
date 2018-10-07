package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import databean.PostBean;
import databean.UserBean;
import formbean.PostFormBean;
import model.Model;
import model.PostDAO;
import model.UserDAO;

public class HomeAction extends Action {

	private static final long serialVersionUID = 1L;

	private UserDAO userDAO;
	private PostDAO postDAO;

	public HomeAction(Model model) {
		userDAO = model.getUserDAO();
		postDAO = model.getPostDAO();
	}

	@Override
	public String getName() {
		return "home.do";
	}

	@Override
	public String performGet(HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute("user");
		if (user == null) {
			// connection broken, back to login
			return "login.do";
		}

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			// check if there's an incoming new post request
			PostFormBean form = new PostFormBean(request);
			request.setAttribute("form", form);

			if (form.isNewPost()) {
				errors.addAll(form.getValidationErrors());
				// do this only if no errors
				if (errors.size() == 0) {
					// create new post in db
					PostBean post = new PostBean();
					post.setEmail(user.getEmail());
					post.setContent(form.getPostContent());
					post.setPostDatetime(new Date());
					postDAO.create(post);
				}
			}
			
			PostBean[] posts = postDAO.getPostsFromUser(user.getEmail());
			request.setAttribute("posts", posts);

			UserBean[] users = userDAO.getUsers();
			request.setAttribute("users", users);

			String userIndex = request.getParameter("userIndex");
			if (userIndex != null) {
				UserBean userSelected = users[Integer.parseInt(userIndex)];
				request.setAttribute("userSelected", userSelected);
			}
			
			return "home.jsp";
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "home.jsp";
		} catch (Exception e) {
			errors.add(e.getMessage());
			return "home.jsp";
		}

	}

	@Override
	public String performPost(HttpServletRequest request) {
		return performGet(request);
	}
}
