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
import org.genericdao.DuplicateKeyException;
import org.genericdao.RollbackException;

import databean.UserBean;
import model.Model;
import formbean.LoginForm;
import model.UserDAO;

public class LoginAction extends Action {

	private static final long serialVersionUID = 1L;


	private UserDAO userDAO;

    public LoginAction(Model model) {
        userDAO = model.getUserDAO();
    }

    public String getName() {
        return "login.do";
    }
	
    @Override
    public String performGet(HttpServletRequest request) {
    	HttpSession session = request.getSession();
		if (session.getAttribute("user") != null) {
			// user already login
			return "home.do";
		}

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			request.setAttribute("users", userDAO.getUsers());

			LoginForm form = new LoginForm(request);
			request.setAttribute("form", form);

			// GET request on this page should avoid error messages
			if ("GET".equals(request.getMethod())) {
				return "login.jsp";
			}

			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "login.jsp";
			}

			UserBean user = userDAO.read(form.getEmail());
			// validate user info
			if (user == null) {
				errors.add("Email not found");
				return "login.jsp";
			}

			if (!form.getPassword().equals(user.getPassword())) {
				errors.add("Incorrect password");
				return "login.jsp";
			}

			// login success
			session.setAttribute("user", user);
			
			return "home.do";

		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}
    }

    @Override
    public String performPost(HttpServletRequest request) {
    	return performGet(request);
    }
}