package databean;

import org.genericdao.PrimaryKey;
import java.util.Date;

@PrimaryKey("commentId")
public class CommentBean implements Comparable<CommentBean>{

	private long commentId;
	private Date commentDatetime;
	private String content;
	private String email;
	private long postId;
	
	public long getCommentId() {
		return commentId;
	}
	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}
	public Date getCommentDatetime() {
		return commentDatetime;
	}
	public void setCommentDatetime(Date commentDatetime) {
		this.commentDatetime = commentDatetime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getPostId() {
		return postId;
	}
	public void setPostId(long postId) {
		this.postId = postId;
	}
	
	@Override
	/**
	 * Comments must be displayed in chronological order (oldest first)
	 */
	public int compareTo(CommentBean commentBean) {
		return this.commentDatetime.compareTo(commentBean.commentDatetime);
	}
	
	
}
