/**
 * angularjs m
 */
var app = angular.module("app", [ 'ngRoute','ngCookies' ])
app.config(function($routeProvider) {
	$routeProvider.when('/registration', {
		templateUrl : 'views/registrationform.html',
		controller : 'UserController'
	}).when('/login', {
		templateUrl : 'views/login.html',
		controller : 'UserController'
	})
	.when('/savejob',{
		templateUrl:'views/jobform.html',
		controller:'JobController'
	})
	.when('/getalljobs' ,{
		templateUrl:'views/jobtitles.html',
		controller:'JobController'
	})
	.when('/saveblogpost',{
		templateUrl:'views/blogpostform.html',
		controller:'BlogPostController'
	})
	.when('/getallblogs',{
		templateUrl:'views/bloglist.html',
		controller:'BlogPostController'
	})
	.when('/getBlogForApproval/:id',{
		templateUrl:'views/approvalform.html',
		controller:'BlogDetailController'
	})
	.when('/getBlogDetail/:id',{
		templateUrl:'views/blogdetail.html',
		controller:'BlogDetailController'
	})
	.otherwise('/',{
		templateUrl : 'views/home.html'
	})
})

app.run(function($rootScope, $location, UserService, $cookieStore) {
	
	if ($rootScope.currentUser == undefined)
		 $rootScope.currentUser = $cookieStore.get("currentUser")
		//console.log($rootScope.currentUser.username)

	$rootScope.logout = function() {
		UserService.logout().then(function(response) {
			$rootScope.currentUser.firstname=null
			$rootScope.currentUser.role=null
			$rootScope.message = "loggedout successfully..."
			delete $rootScope
			$cookieStore.remove("currentUser")
			$location.path('/login')
		}, function(response) {
			console.log(response.status)
			$rootScope.message = response.data.message
			$location.path('/login')

		})
	}
})