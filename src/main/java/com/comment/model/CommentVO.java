package com.comment.model;

import java.io.InputStream;
import java.sql.Date;
import java.sql.Timestamp;

public class CommentVO implements java.io.Serializable {

	private Integer commentId;
	private Integer articleId;
	private Integer memberId;
	private String commentContent;
	private Integer commentStatus;
	private Timestamp commentPostTime;
	private Timestamp commentUpdateTime;
	private byte[] commentPicture;

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Integer getCommentStatus() {
		return commentStatus;
	}

	public void setCommentStatus(Integer commentStatus) {
		this.commentStatus = commentStatus;
	}

	public Timestamp getCommentPostTime() {
		return commentPostTime;
	}

	public void setCommentPostTime(Timestamp commentPostTime) {
		this.commentPostTime = commentPostTime;
	}

	public Timestamp getCommentUpdateTime() {
		return commentUpdateTime;
	}

	public void setCommentUpdateTime(Timestamp commentUpdateTime) {
		this.commentUpdateTime = commentUpdateTime;
	}

	public byte[] getCommentPicture() {
		return commentPicture;
	}

	public void setCommentPicture(byte[] commentPicture) {
		this.commentPicture = commentPicture;
	}
	// for join memberName from memberId
	 public com.mem.model.MemVO getMemVO() {
		    com.mem.model.MemService memSvc = new com.mem.model.MemService();
		    com.mem.model.MemVO memVO = memSvc.getOneMem(memberId);
		    return memVO;
    }

}
