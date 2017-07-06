package com.niit.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="blogcomment")
public class BlogComment {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@ManyToOne
	
	private Users commentedBy;
	private Date commentOn;
	@ManyToOne
	@JoinColumn(name="blogpost_id")
	private BlogPost blogPost;
	private String body;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Users getCommentedBy() {
		return commentedBy;
	}
	public void setCommentedBy(Users commentedBy) {
		this.commentedBy = commentedBy;
	}
	public Date getCommentOn() {
		return commentOn;
	}
	public void setCommentOn(Date commentOn) {
		this.commentOn = commentOn;
	}
	public BlogPost getBlogPost() {
		return blogPost;
	}
	public void setBlogPost(BlogPost blogPost) {
		this.blogPost = blogPost;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	

}
