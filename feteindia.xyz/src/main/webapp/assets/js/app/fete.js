var app = angular.module("fete",['ngRoute','ng.group']);


app.config(['$routeProvider',function($routeProvider){
	
	console.log("fete app");

	$routeProvider.
	when('/addRestaurant',{
		templateUrl: "templates/restaurant/addRestaurant.html",
		controller: "restaurantConroller"
	}).	when('/RegistrationSuccess',{
		templateUrl: "templates/restaurant/RegistrationSuccess.html"
	}).	when('/login',{
		templateUrl: "templates/restaurant/Login.html",
		controller: "loginConroller"
	}).	when('/userDashboard',{
		templateUrl: "jsp/userDashboard.jsp"
	}).when('/RestaurantDashBoard',{
		templateUrl: "templates/restaurant/RestaurantDashBoard.html"
	}).when('/home',{
		templateUrl: "templates/home.html"
	}).when('/accountDetails',{
		templateUrl: "jsp/accountDetails.jsp",
		controller: "accountController"
	}).when('/myBookings',{
		templateUrl: "jsp/myBookings.jsp"
	}).when('/myHall',{
		templateUrl: "jsp/myHall.jsp",
		controller: "venueController"
	}).when('/myMenu',{
		templateUrl: "jsp/myMenu.jsp",
		controller: "menuController"
	}).when('/myDjDetails',{
		templateUrl: "jsp/djDetails.jsp"
	}).when('/myDecoration',{
		templateUrl: "jsp/myDecoration.jsp"
	}).when('/myPackages',{
		templateUrl: "jsp/partyPackages.jsp",
		controller: "packagesController"
	}).when('/myCoupon',{
		templateUrl: "jsp/discount.jsp"
	}).when('/myTransport',{
		templateUrl: "jsp/transportation.jsp"
	}).when('/otherServices',{
		templateUrl: "jsp/otherServices.jsp"
	}).when('/address',{
		templateUrl: "jsp/location.jsp"
	}).
	otherwise({
		redirectTo: 'home'
	});
}]);

app.run(function($rootScope) {
	$rootScope.sessionUser = sessionStorage.getItem("sessionUser");
	$rootScope.alertMe = sessionStorage.getItem("reg");
	$rootScope.today = new Date();
});

app.directive('fileModel', ['$parse', function ($parse) {
    return {
       restrict: 'A',
       link: function(scope, element, attrs) {
          var model = $parse(attrs.fileModel);
          var modelSetter = model.assign;
          
          element.bind('change', function(){
             scope.$apply(function(){
                modelSetter(scope, element[0].files[0]);
             });
          });
       }
    };
 }]);

app.service('fileUpload', ['$http', function ($http) {
    this.uploadFileToUrl = function(file, uploadUrl){
       var fd = new FormData();
       fd.append('file', file);
    
       $http.post(uploadUrl, fd, {
          transformRequest: angular.identity,
          headers: {'Content-Type': undefined}
       })
    
       .success(function(){
       })
    
       .error(function(){
       });
    }
 }]);