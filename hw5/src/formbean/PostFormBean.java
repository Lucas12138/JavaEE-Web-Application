package formbean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class PostFormBean {

	private String postContent;
	private String button;
	
	public PostFormBean(HttpServletRequest request) {
		postContent = request.getParameter("post");
		button = request.getParameter("button");
	}
	
	public String getPostContent() {
		return postContent;
	}
	public String getButton() {
		return button;
	}
	
	public boolean isNewPost() {
		// true if it's new post (not null)
		return postContent != null;
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		// check empty
		if (postContent.length() == 0)
			errors.add("Please write something before your post");

		// if null or empty: return directly to avoid exception
		if (errors.size() > 0)
			return errors;

		if (!button.equals("Submit"))
			errors.add("Invalid button");

		// characters limitation
		if (postContent.length() > 140)
			errors.add("Please post no more than 140 characters");

		return errors;
	}
	
	
}
