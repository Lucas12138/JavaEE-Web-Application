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
import model.Model;
import model.UserDAO;

public class VisitorAction extends Action {

    private static final long serialVersionUID = 1L;

    private UserDAO userDAO;

	public VisitorAction(Model model) {
		userDAO = model.getUserDAO();
	}

	@Override
	public String getName() {
		return "visitor.do";
	}

	@Override
	public String performGet(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);

        try {
            UserBean[] users = userDAO.getUsers();
        	request.setAttribute("users", users);
        	
        	String userIndex = request.getParameter("userIndex");
        	UserBean userSelected = users[Integer.parseInt(userIndex)];
        	
            request.setAttribute("users", users);
            request.setAttribute("userSelected", userSelected);
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