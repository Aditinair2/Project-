/**
 * 
 */
app.controller('UserController', function(UserService, $scope, $location,$rootScope, $cookieStore) {
	$scope.registrationSuccess=''
	$scope.register = function() {
		UserService.registerUser($scope.user).then(function(response) {
			$scope.registrationSuccess = "Registered successfully..please login.."
			$location.path('/login')
		}, function(response) {
			$scope.error = response.data;
			$location.path('/registration')
		})
	}
	$scope.login = function() {
		UserService.login($scope.user).then(function(response) {
			$rootScope.currentUser = response.data
			$cookieStore.put("currentUser", response.data)
			$location.path('/home')

		}, function(response) {
			$scope.message = response.data.message
			$location.path('/login')
		})

	}
	$scope.user=UserService.getUserByUsername().then(function(represent){
		$scope.user=response.data
	},function(response){
		console.log(response.status)
	})
	
	$scope.update=function(){
		UserService.updateUserProfile($scope.userobj).then(function(response){
			$scope.message="Update theprofile successfully"
		},function(response){
			console.log(response.data)
		
		})
	}
		
	
	
})
	