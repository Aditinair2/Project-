/**
 * 
 */
app.controller('FriendController',function($scope,$location,FriendService){
	function listOfSuggestedUsers(){
		$scope.suggestUsers=FriendService.suggestedUsers().then(function(response){
			$scope.suggestedUsers=response.data;
			
		},function(response){
			console.log(response.status);
		})
		
	}
	$scope.friendrequest=function(toUsername){
		FriendService.sendFriendRequest(toUsername).then(function(response){
			alert("Friendrequest to'+toUsername +' has been sent successfully..")
			$location.path('/suggestedusers')
		},function(response){
			console.log(response.status)
		})
	}
	listOfSuggestedUsers();
	})