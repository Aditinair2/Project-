/**
 * 
 */
app.factory('JobService',function($http){
	var jobService={}
	jobService.saveJob=function(job){
		return $http.post("https://localhost:8080/backend_project2",job)
	}
	return jobservice;
})