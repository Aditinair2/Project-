/**
 * 
 */
app.controller('UserController', function(UserService, $scope, $location,
		$rootScope, $cookieStore) {
	$scope.register = function() {
		UserService.registerUser($scope.user).then(function(response) {
			$scope.message = "Registered successfully..please login.."
			$location.path('/login')
		}, function(response) {
			$scope.error = response.data;
			$location.path('/registration')
		})
	}
	$scope.login = function() {
		Userservice.login($scope.user).then(function(response) {
			$rootScope.currentUser = response.data
			$cookieStore.put("currentUser", response.data)
			$location.path('/home')

		}, function(response) {
			scope.mesaage = response.data.message
			$location.path('/login')
		})

	}

})