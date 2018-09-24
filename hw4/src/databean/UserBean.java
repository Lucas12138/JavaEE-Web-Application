package databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("email")
public class UserBean implements Comparable<UserBean> {

	private String email;
	private String password;
	private String firstName;
	private String lastName;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	/**
	 * Use this method, to keep the names on the navbar nicer
	 */
	public int compareTo(UserBean user) {
		// compare first name first, if same, compare last name
		int compareRes = this.getFirstName().compareTo(user.getFirstName());
		if (compareRes != 0) {
			return compareRes;
		} else {
			return this.getLastName().compareTo(user.getLastName());
		}
	}

}
