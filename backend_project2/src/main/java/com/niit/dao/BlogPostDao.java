package com.niit.dao;

import java.util.List;

import com.niit.model.BlogComment;
import com.niit.model.BlogPost;

public interface BlogPostDao {
	void saveBlogPost(BlogPost blogPost);
	
	List<BlogPost> getAllBlogs(boolean approved);

	BlogPost getBlogById(int id);

	void updateBlogPost(BlogPost blogPost);

	void addcomment(BlogComment blogComment);

}
