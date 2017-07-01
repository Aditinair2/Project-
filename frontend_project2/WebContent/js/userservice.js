/**
 * 
 */
app.factory('UserService',function($http){
	var userService={}
	
	userService.registerUser=function(user){
		return $http.post("https://localhost:8080/backend_project2/registration",user)
		
	}
	userService.login=function(user){
		return $http.post("https://localhost:8080/backend_project2/login",user)
		
	}
	return userService;
	
	
	
})