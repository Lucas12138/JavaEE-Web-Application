

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Calculator
 */
@WebServlet("/Calculator")
public class Calculator extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String INVALID_X_ERROR_MESSAGE = "X is not a number";
	private static final String INVALID_Y_ERROR_MESSAGE = "Y is not a number";
	private static final String INVALID_OPERATION_ERROR_MESSAGE = "Don't change the operation value!";
	private static final String INVALID_DIVISION_ERROR_MESSAGE = "Cannot divide by 0";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Calculator() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		outputHtml(response, request.getMethod(), null, null, null);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<String> messageList = new ArrayList<>();
		
		String xString = request.getParameter("x");
		String yString = request.getParameter("y");
		String operation = request.getParameter("op");
		double x = -1;
		double y = -1;
		
		
		try {
			x = Double.parseDouble(xString);
		} catch (Exception e) {
			messageList.add(INVALID_X_ERROR_MESSAGE);
		}
		
		try {
			y = Double.parseDouble(yString);
		} catch (Exception e) {
			messageList.add(INVALID_Y_ERROR_MESSAGE);
		}
		
		if (y == 0) {
			messageList.add(INVALID_DIVISION_ERROR_MESSAGE);
		}
		
		if (messageList.size() > 0) {
			outputHtml(response, request.getMethod(), xString, yString, messageList);
			return;
		}
		
		double answer = calculate(x, y, operation, messageList);
		if (messageList.size() > 0) {
			outputHtml(response, request.getMethod(), xString, yString, messageList);
			return;
		}
		
		DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
		
		messageList.add(decimalFormat.format(x) + " " + operation + " " + decimalFormat.format(y) + " = " + decimalFormat.format(answer));
		outputHtml(response, request.getMethod(), xString, yString, messageList);
	}

	private double calculate(double x, double y, String operation, List<String> messageList) {
		double calculatedResult = 0;
		switch (operation) {
			case "+":
				calculatedResult = x + y;
				break;
			case "-":
				calculatedResult = x - y;
				break;
			case "*": 
				calculatedResult = x * y;
			    break;
			case "/": 
				calculatedResult = x / y;
			    break;
			default:  
				messageList.add(INVALID_OPERATION_ERROR_MESSAGE);
				break;
		}
		return calculatedResult;
	}
	
	/**
	 * Show the calculator page
	 * @param response
	 * @param requestMethod request method type (GET/POST)
	 * @param xString y value
	 * @param yString x value
	 * @param messages possible messages (including final result or errors)
	 */
	private void outputHtml(HttpServletResponse response, String requestMethod, String xString, String yString, List<String> messages)
			throws IOException {
		response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<!doctype html>");
		out.println("<html lang=\"en\">");
		out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
		out.println("<title>CMU's Best Calculator</title>");
		out.println("</head>");
		out.println("<body>");
	
		out.println("<div style=\"font-size:22px; height:100px; width:260px; position:fixed; top: 5%; left: 40%;\">");
		out.println("<h2>I'm a cooler calculator :)</h2>");
		// check request method
		if (requestMethod != null) {
			out.println("<h4 style=\"color:yellow\">" + "Request Method: "+ requestMethod + "</h4>");
		}
		out.println("<form action=\"Calculator\" method=\"POST\">");
		out.println("<table style=\"background-color:green; color:white;\">");
		
		// input row for x
		out.println("<tr style=\"background-color:purple; color:white;\">");
		out.println("<td>X: </td>");
		if (xString == null) {
			out.println("<td><input size=\"40\" type=\"text\" name=\"x\" value=\"\"></td>");
		}else {
			out.println("<td><input size=\"40\" type=\"text\" name=\"x\" value=\""+ xString + "\"></td>");
		}
		out.println("</tr>");
		
		// input row for y
		out.println("<tr style=\"background-color:purple; color:white;\">");
		out.println("<td>Y: </td>");
		if (yString == null) {
			out.println("<td><input size=\"40\" type=\"text\" name=\"y\" value=\"\"></td>");
		}else {
			out.println("<td><input size=\"40\" type=\"text\" name=\"y\" value=\""+ yString + "\"></td>");
		}
		out.println("</tr>");
		
		// operations
		out.println("<tr style=\"background-color:purple; color:white;\">");
		out.println("<td colspan=\"2\" style=\"text-align:center;\">");
		out.println("    <input type=\"submit\" name=\"op\" value=\"+\">");
		out.println("    <input type=\"submit\" name=\"op\" value=\"-\">");
		out.println("    <input type=\"submit\" name=\"op\" value=\"*\">");
		out.println("    <input type=\"submit\" name=\"op\" value=\"/\">");
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</form>");
		
		// display messages
		if (messages != null) {
			for (String message : messages) {
				out.println("<h2 style=\"color:red;\">");
				out.println(message);
				out.println("</h2>");
			}
		}
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}

}
