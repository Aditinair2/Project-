/**
 * 
 */
app.controller('UserController', function(UserService, $scope, $location,$rootScope, $cookieStore,$window) {
	
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
			$window.alert("Username or password is incorrect!Try Again")
			$location.path('/login')
			console.log(response.data)
		})

	}
	$scope.user=UserService.getUserByUsername().then(function(represent){
		$scope.user=response.data
	},function(response){
		console.log(response.status)
	})
	
	$scope.update=function(){
		UserService.updateUserProfile($scope.user).then(function(response){
			$scope.message="Updated the profile successfully"
		},function(response){
			console.log(response.data)
		
		})
	}
		
	
	
})
	