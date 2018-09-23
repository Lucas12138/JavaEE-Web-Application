package formbean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class RegisterForm {

	private String email;
	private String password;
	private String passwordConfirmed;
	private String firstName;
	private String lastName;
	private String button;

	public RegisterForm(HttpServletRequest request) {
		email = request.getParameter("email");
		password = request.getParameter("password");
		passwordConfirmed = request.getParameter("passwordConfirmed");
		firstName = request.getParameter("firstName");
		lastName = request.getParameter("lastName");
		button = request.getParameter("button");
	}
	
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public String getPasswordConfirmed() {
		return passwordConfirmed;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getButton() {
		return button;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (email == null || email.length() == 0)
			errors.add("Email is required");
		if (password == null || password.length() == 0)
			errors.add("Password is required");
		if (passwordConfirmed == null || passwordConfirmed.length() == 0)
			errors.add("Password need to be confirmed");
		
		if (button == null)
			errors.add("Button is required");

		if (errors.size() > 0)
			return errors;

		if (!button.equals("Register"))
			errors.add("Invalid button");
		if (email.matches(".+@.+"))
			errors.add("Invalid email format");
		if (!passwordConfirmed.equals(password))
			errors.add("Password confirmed doesn't match the password");

		return errors;
	}
	
}
