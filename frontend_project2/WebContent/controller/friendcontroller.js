/**
 * 
 */
app.controller('FriendController',function($scope,$location,FriendService){
	function listOfSuggestedUsers(){
		$scope.suggestedUsers=FriendService.suggestedUsers().then(function(response){
			$scope.suggestedUsers=response.data
			
		},function(response){
			console.log(response.status);
		})
		
	}
	function pendingRequests(){
		$scope.listOfPendingRequests=FriendService.pendingRequests().then(function(response){
			$scope.listOfPendingRequests=response.data
			$scope.message=response.data.message
		},function(response){
			$window.alert("Fetching data failed")
			console.log(response.status);
		
		})
	}
	
	$scope.friendrequest=function(toUsername){
		FriendService.sendFriendRequest(toUsername).then(function(response){
			listOfSuggestedUsers();
			$location.path('/suggestedusers')
			alert("Friendrequest to'+toUsername +' has been sent successfully..")
		},function(response){
			$window.alert("Friend request failed")
			console.log(response.status)
		})
	}
	$scope.updatePendingRequest=function(fromId,status){
		FriendService.updatePendingRequest(fromId,status).then(function(response){
			$window.alert("Friend added successfully")
			pendingRequests();
			$location.path('/pendingrequests')
		},function(response){
			$window.alert("Failed to process action")
			console.log(response);
		
		})
	}
	function listOfFriends(){
		FriendService.listOfFriends().then(function(response){
			$scope.listOfPendingRequests=response.data
			console.log(response.data)
		},function(response){
			$window.alert("failes to fetch Friend List")
			console.log(response.status);
		
		})
	}
	$scope.friendDetails=function(username){
		$scope.showDetails=false
		FriendService.getDetails(username).then(function(response){
			$scope.showDetails=true
			$scope.friendDetails=response.data
		},function(response){
			$window.alert("Failed to fetch Friend Details. Read console for details")
			console.log(response.status)
		})
	}
		
		
	
	listOfFriends();
	pendingRequests();
	listOfSuggestedUsers();
})