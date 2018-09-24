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

@WebServlet("/Visitor")
public class Visitor extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserDAO userDAO;

    public void init() throws ServletException {
        ServletContext context = getServletContext();
        String jdbcDriverName = context.getInitParameter("jdbcDriverName");
        String jdbcURL = context.getInitParameter("jdbcURL");

        try {
            ConnectionPool cp = new ConnectionPool(jdbcDriverName, jdbcURL);
    
            cp.setDebugOutput(System.out);  // Print out the generated SQL

            userDAO = new UserDAO(cp, "user");
        } catch (DAOException e) {
            throw new ServletException(e);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("Login");
            return;
        }

        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);

        try {
            request.setAttribute("users", userDAO.getUsers());
            
            RequestDispatcher d = request.getRequestDispatcher("home.jsp");
            d.forward(request, response);
        } catch (RollbackException e) {
            errors.add(e.getMessage());
            RequestDispatcher d = request.getRequestDispatcher("home.jsp");
            d.forward(request, response);
        }
    }
}