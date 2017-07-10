/**
 * 
 */
app.factory('BlogPostService',function($http){
	var blogPostService={}
	
	blogPostService.saveBlog=function(blogPost){
		return $http.post("http://localhost:8080/backend_project2/saveblogpost",blogPost)
	}
	
	blogPostService.blogsApproved=function(){
		return $http.get("http://localhost:8080/backend_project2/listofblogs/"+true)
	}
	blogPostService.blogsWaitingForApproval=function(){
		return $http.get("http://localhost:8080/backend_project2/listofblogs/"+false)
	}
	blogPostService.getBlogPost=function(id){
		return $http.get("http://localhost:8080/backend_project2/getblogpost/"+id)
	}
	blogPostService.updateBlogPost=function(blogPost){
		return $http.put("http://localhost:8080/backend_project2/updateblogpost",blogPost)
	}
	blogPostService.addComment=function(blogComment){
		return $http.post("http://localhost:8080/backend_project2/addblogcomment",blogComment)
	}
	blogPostService.getBlogComments=function(blogId){
		return $http.get("http://localhost:8080/backend_project2/getblogcomments/"+blogId)
	}
	return blogPostService;
})