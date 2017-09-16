/**
 * 
 */
app.factory("MessageService",function($http){
	var messageService={};
	
	messageService.sendMessage=function(message){
		return $http.post("http://localhost:8080/backend_project2/sendmessage",message)
	}
	
	messageService.viewMessage=function(username){
		return $http.get("http://localhost:8080/backend_project2/getmessage/"+username)
	}
	return messageService;
})