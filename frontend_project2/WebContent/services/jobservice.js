/**
 * 
 */
app.factory('JobService', function($hhtp) {
	var jobService ={}
	jobService.saveJob = function(job) {
		return $http.post("http://localhost:8080/backend_project2/savejob", job)
	}
	jobService.getAllJobs=function(){
		return $http.get("http://localhost:8080/backend_project2/getalljobs",job)
	}
	return jobService
})