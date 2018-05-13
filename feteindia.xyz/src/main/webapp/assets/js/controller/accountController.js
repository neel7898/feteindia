/**
 * 
 */
app.controller("accountController", function($scope,$http,$rootScope,userService) {
	console.log('account controller loaded');
	
	$scope.user = {};
	
	$scope.editFlag = true;
	$scope.descFlag = true;
	$scope.menuCardSize = 0;
	$scope.venueSize = 0;
	
	var menuCard = userService.getExistMenuList();
	if(menuCard == undefined){
		$scope.menuCardSize = 0;
	}else{
		$scope.menuCardSize = menuCard.length;
	}
	
	var venue = userService.getPartyHalls();
	
	if(venue == undefined){
		$scope.venueSize = 0;
	}else{
		$scope.venueSize = venue.length;
	}
	
	var packages = userService.getPartyHalls();
	
	if(packages == undefined){
		$scope.packageSize = 0;
	}else{
		$scope.packageSize = packages.length;
	}
	
	
	$scope.loadRestAddress = function(){
		 $http({
		        url: '../rest/loadRestAddress',
		        method: 'POST',
		        headers: {
		            'Content-Type': 'application/x-www-form-urlencoded'
		        }, params: {
		            id: $scope.addId
		        }
		    }).success(function(data){
		    	console.log(data);
		    	/*$scope.user.add = data.addressLine+', '+data.city+', '+data.state+', '+data.pinCode;*/
		    	$scope.user.add = data;
		    	$rootScope.currentAccount = $scope.user;
		    }).error(function(){});
	}
	
	$scope.loadAccountDetails = function(){
		$scope.feteId = sessionStorage.getItem("feteId");
		console.log($scope.feteId);
		 $http({
		        url: '../rest/loadAccountDetails',
		        method: 'POST',
		        headers: {
		            'Content-Type': 'application/x-www-form-urlencoded'
		        }, params: {
		            id: $scope.feteId
		        }
		    }).success(function(data){
		    	console.log(data);
		    	$scope.user = data;
		    	$scope.addId = data.address.hibernateLazyInitializer.identifier;
		    	console.log($scope.addId);
		    	$scope.loadRestAddress();
		    }).error(function(){});
	}
	
	$scope.user1 = {};
	
	$scope.changeEditFlag = function(){
		if($scope.editFlag){
			$scope.editFlag = false;
		}else{
			$scope.editFlag = true;
		}
	}
	
	$scope.changeDescFlag = function(){
		if($scope.descFlag){
			$scope.descFlag = false;
		}else{
			$scope.descFlag = true;
		}
	}
	
	$scope.updateDetails = function(user1){
		$http.post('../rest/updateAccountDetail',user1).success(function(data){
	    	$scope.loadAccountDetails();
	    }).error(function(){});
		$scope.changeEditFlag();
	}
	
	$scope.updateDesc = function(user1){
		$scope.user2 = user1;
		$http.post('../rest/updateAccountDetail',$scope.user2).success(function(data){
	    	$scope.loadAccountDetails();
	    }).error(function(){});
		$scope.changeDescFlag();
	}
	
	$scope.loadAccountDetails();
});
