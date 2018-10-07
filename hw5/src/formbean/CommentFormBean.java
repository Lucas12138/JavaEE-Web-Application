package formbean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class CommentFormBean {
	private String commentContent;
	private String button;
	
	public CommentFormBean(HttpServletRequest request) {
		commentContent = request.getParameter("comment");
		button = request.getParameter("button");
	}
	
	public String getCommentContent() {
		return commentContent;
	}
	public String getButton() {
		return button;
	}
	
	public boolean isNewComment() {
		// true if it's new post (not null)
		return commentContent != null;
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		// check empty
		if (commentContent.length() == 0)
			errors.add("Please write something before your comment");

		// if null or empty: return directly to avoid exception
		if (errors.size() > 0)
			return errors;

		if (!button.equals("Comment"))
			errors.add("Invalid button");

		// characters limitation
		if (commentContent.length() > 140)
			errors.add("Please comment no more than 140 characters");

		return errors;
	}
}
