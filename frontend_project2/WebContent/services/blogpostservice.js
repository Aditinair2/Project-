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
		return $http.post("http://localhost:8080/backend_project2/listofblogs/"+false)
	}
	blogPostService.getBlogPost=function(id){
		return $http.get("http://localhost:8080/backend_project2/getblogpost/"+id)
	}
	blogPostService.updateBlogPost=function(){
		return $http.put("/http://localhost:8080/backend_project2/updateblogpost",blogpost)
	}
	blogPostService.addComment=function(){
		return $http.post("/http://localhost:8080/backend_project2/addblogcomment",blogComment)
	}
	return blogPostService;
})