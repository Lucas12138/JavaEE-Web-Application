package controller;

import java.io.IOException;
import java.util.ArrayList;
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

import databean.UserBean;
import formbean.RegisterForm;
import model.Model;
import model.UserDAO;

public class RegisterAction extends Action {

	private static final long serialVersionUID = 1L;

	private UserDAO userDAO;

	public RegisterAction(Model model) {
		userDAO = model.getUserDAO();
	}

	public String getName() {
		return "register.do";
	}
	
	@Override
	public String performGet(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null) {
			return "home.do";
		}

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			request.setAttribute("users", userDAO.getUsers());

			RegisterForm form = new RegisterForm(request);
			request.setAttribute("form", form);

			// GET request on this page should avoid error messages
			if ("GET".equals(request.getMethod())) {				
				return "register.jsp";
			}

			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "register.jsp";
			}

			// create new user in db
			UserBean user = new UserBean();
			user.setEmail(form.getEmail());
			user.setPassword(form.getPassword());
			user.setFirstName(form.getFirstName());
			user.setLastName(form.getLastName());

			userDAO.create(user);

			// put current user into session
			session.setAttribute("user", user);
			return "home.do";

		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "register.jsp";
		}
	}
	
	
	@Override
	public String performPost(HttpServletRequest request) {
		return performGet(request);
	}
}