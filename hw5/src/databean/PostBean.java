package databean;


import org.genericdao.PrimaryKey;
import java.util.Date;

@PrimaryKey("postId")
public class PostBean implements Comparable<PostBean>{
	
	private long postId;
	private Date postDatetime;
	private String content;
	private String email;
	
	public long getPostId() {
		return postId;
	}
	public void setPostId(long postId) {
		this.postId = postId;
	}
	public Date getPostDatetime() {
		return postDatetime;
	}
	public void setPostDatetime(Date postDatetime) {
		this.postDatetime = postDatetime;
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
	
	@Override
	/**
	 * Posts must be displayed in reverse chronological order (newest first)
	 */
	public int compareTo(PostBean postBean) {
		return postBean.postDatetime.compareTo(this.postDatetime);
	}
}
