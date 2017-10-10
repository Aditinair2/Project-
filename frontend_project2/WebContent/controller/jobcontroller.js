/**
 * 
 * */

app.controller ('JobController',function($scope,$location,JobService,$window){
	
	function getAllJobs(){
	$scope.jobs=JobService.getAllJobs().then(function (response){
		$scope.jobs=response.data;
	},function(response){
		
	    console.log(response.status)
	
	})
	}
	getAllJobs();
	$scope.saveJob=function(){
		JobService.saveJob($scope.job).then(function(response){
			$location.path('/getalljobs')
			},function(response){
				$scope.message=response.data.message
				if(response.status==401)
					$location.path('/login')
				if(response.status==500)
					$location.path('/savejob')
			})
		}
	$scope.getJobDetail=function(id){
		$scope.showDetails=true;
		JobService.getJobById(id).then(function(response){
			$scope.job=response.data;
			$window.alert("entering job detail function");
		},function(response){
			console.log(response.status);
		
		})
	}

	})
