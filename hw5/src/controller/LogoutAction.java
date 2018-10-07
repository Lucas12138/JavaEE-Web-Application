package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Model;


public class LogoutAction extends Action {

    private static final long serialVersionUID = 1L;

    public LogoutAction(Model model) {
    	
    }
    
    @Override
    public String getName() {
        return "logout.do";
    }
    
    @Override
	public String performGet(HttpServletRequest request) {
		return performPost(request);
	}
	@Override
	public String performPost(HttpServletRequest request) {
		HttpSession session = request.getSession();
        session.setAttribute("user", null);
        return "login.do";
	}
    
}