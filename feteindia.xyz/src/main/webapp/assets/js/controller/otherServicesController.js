/**
 * 
 */
app.controller("otherServicesController",function($scope,$http,fileUpload) {
	console.log('otherServices controller loaded');
	$scope.feteId = sessionStorage.getItem("feteId");
	$scope.noOtherServicesFlag = true;
	$scope.otherServicesList = [];
	
	$scope.existOtherServicesList = [];
	
	$scope.getExistOtherServicesList = function(){
		$http.post('rest/getOtherServicesListByFeteId',$scope.feteId).success(function(data) {
			console.log(data);
			$scope.existOtherServicesList = data;
			if($scope.existOtherServicesList.length != 0){
				try{
				if($scope.existOtherServicesList.message != "error"){	
				$scope.noOtherServicesFlag = false;
				}else{
					$scope.noOtherServicesFlag = true;
				}
				}catch(err){
					
				}
			}
		}).error(function(){
			console.log("some error");
		})
	}
	
	$scope.getExistOtherServicesList();
	
	$scope.addToOtherServicesList = function(otherServices){
		$scope.otherServicesList.push(otherServices);
		console.log($scope.otherServicesList);
		$scope.otherServices = {};
		$scope.otherServices.file = {};
	}
	
	$scope.removeItemFromList = function(m){
		$scope.otherServicesList.splice(m, 1);
	}
	
	console.log($scope.categoryWiseOtherServices);
	
	$scope.editOtherServices = {};
	
	$scope.editModal = function(m){
		$scope.editOtherServices = m;
	}
	
	$scope.updateOtherServices = function(otherServices){
		$http.post('rest/updateSingleOtherServicesItem',otherServices).success(function(data) {
        	console.log("success");
        	$scope.getExistOtherServicesList();
        	$scope.editOtherServices = {};
        	$("[data-dismiss=modal]").trigger({ type: "click" });
        }).error(function(){       	
        });
	};
	
	$scope.deleteOtherServicesItem = function(otherServices){
		$http.post('rest/deleteSingleOtherServicesItem',otherServices).success(function(data) {
        	console.log("success");
        	$scope.getExistOtherServicesList();
        }).error(function(){       	
        });
	};
	
	$scope.addToDatabase = function(){
		
		angular.forEach($scope.otherServicesList,function(m){
			m.feteId = $scope.feteId;
		});
		
		angular.forEach($scope.otherServicesList,function(m){
			console.log(m);
			var file = m.file;
			$http.post('rest/addSingleOtherServicesItem',m).success(function(data) {
	        	console.log("success");
	        }).error(function(){       	
	        });
			fileUpload.uploadFileToUrl(file, 'rest/uploadPhoto');
		});
		$scope.getExistOtherServicesList();
		$scope.otherServices = {};
    	$scope.otherServicesList = [];
    	$("[data-dismiss=modal]").trigger({ type: "click" });
	};
	
});