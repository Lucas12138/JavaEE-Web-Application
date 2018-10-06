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

import dao.UserDAO;
import databean.UserBean;
import formbean.LoginForm;

@WebServlet("/Login")
public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private UserDAO userDAO;

	public void init() throws ServletException {
		ServletContext context = getServletContext();
		String jdbcDriverName = context.getInitParameter("jdbcDriverName");
		String jdbcURL = context.getInitParameter("jdbcURL");

		try {
			ConnectionPool cp = new ConnectionPool(jdbcDriverName, jdbcURL);

			cp.setDebugOutput(System.out); // Print out the generated SQL

			userDAO = new UserDAO(cp, "zizhel_user");
		} catch (DAOException e) {
			throw new ServletException(e);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null) {
			// user already login
			response.sendRedirect("Home");
			return;
		}

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			request.setAttribute("users", userDAO.getUsers());

			LoginForm form = new LoginForm(request);
			request.setAttribute("form", form);

			// GET request on this page should avoid error messages
			if ("GET".equals(request.getMethod())) {
				RequestDispatcher d = request.getRequestDispatcher("login.jsp");
				d.forward(request, response);
				return;
			}

			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				RequestDispatcher d = request.getRequestDispatcher("login.jsp");
				d.forward(request, response);
				return;
			}

			UserBean user = userDAO.read(form.getEmail());
			// validate user info
			if (user == null) {
				errors.add("Email not found");
				RequestDispatcher d = request.getRequestDispatcher("login.jsp");
				d.forward(request, response);
				return;
			}

			if (!form.getPassword().equals(user.getPassword())) {
				errors.add("Incorrect password");
				RequestDispatcher d = request.getRequestDispatcher("login.jsp");
				d.forward(request, response);
				return;
			}

			// login success
			session.setAttribute("user", user);
			response.sendRedirect("Home");

		} catch (RollbackException e) {
			errors.add(e.getMessage());
			RequestDispatcher d = request.getRequestDispatcher("error.jsp");
			d.forward(request, response);
		}
	}
}