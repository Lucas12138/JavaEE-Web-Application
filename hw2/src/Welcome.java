

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Welcome
 */
@WebServlet("/index.html")
public class Welcome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Welcome() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		out.println("<!doctype html>");
		out.println("<html lang=\"en\">");
		out.println("    <head>");
        out.println("        <meta charset=\"UTF-8\">");
		out.println("        <title>Welcome to the calculator!</title>");
		out.println("    </head>");
		out.println("    <body>");
		out.println("	 	 <div style=\"font-size:27px; height:100px; width:200px; position:fixed; top: 15%; left: 40%;\">");
		out.println("	 	 <h2>Welcome!</h2>");
		out.println("        <hr>");
		out.println("		 <p>Remote Host Address:" + request.getRemoteAddr() + "</p>");
		out.println(" 		 <p>Remote Host Name:" + request.getRemoteHost() + "</p>");
		out.println("  	 	 <form action=\"Calculator\" method=\"GET\">");
		out.println("    	 	 <input type=\"submit\" value=\"Calculator\" style=\"font-size:27px; padding:20px 32px; color:#b00;\">");
		out.println("  	 	 </form>");
		out.println("	 	 </div>");
		out.println("        <br>");
		out.println("    </body>");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
