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

		// check null and empty
		if (email == null || email.length() == 0)
			errors.add("Email is required");

		if (password == null || password.length() == 0)
			errors.add("Password is required");

		if (passwordConfirmed == null || passwordConfirmed.length() == 0)
			errors.add("Password needs to be confirmed");

		if (firstName == null || firstName.length() == 0)
			errors.add("First name is required");

		if (lastName == null || lastName.length() == 0)
			errors.add("Last name is required");

		if (button == null)
			errors.add("Button is required");

		// if null or empty: return directly to avoid exception
		if (errors.size() > 0)
			return errors;

		if (!button.equals("Register"))
			errors.add("Invalid button");

		// RFC 5322 standard
		if (!email.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"))
			errors.add("Invalid email format");

		// 2 passwords have to match
		if (!passwordConfirmed.equals(password))
			errors.add("Password confirmed doesn't match the password");

		// accept leading or trailing whitespace
		// only letters are accepted
		firstName = firstName.trim();
		if (!firstName.matches("[a-zA-Z]+"))
			errors.add("Invalid first name");

		lastName = lastName.trim();
		if (!lastName.matches("[a-zA-Z]+"))
			errors.add("Invalid last name");

		return errors;
	}

}
