/**
 * 
 */
app.controller("packagesController",function($scope,$http,fileUpload) {
	console.log('packages controller loaded');
	$scope.feteId = sessionStorage.getItem("feteId");
	$scope.noPackagesFlag = true;
	$scope.packagesList = [];
	
	$scope.existPackagesList = [];
	
	$scope.getExistPackagesList = function(){
		$http.post('rest/getPackagesListByFeteId',$scope.feteId).success(function(data) {
			console.log(data);
			$scope.existPackagesList = data;
			if($scope.existPackagesList.length != 0){
				try{
				if($scope.existPackagesList.message != "error"){	
				$scope.noPackagesFlag = false;
				}else{
					$scope.noPackagesFlag = true;
				}
				}catch(err){
					
				}
			}
		}).error(function(){
			console.log("some error");
		})
	}
	
	$scope.getExistPackagesList();
	
	$scope.addToPackagesList = function(packages){
		$scope.packagesList.push(packages);
		console.log($scope.packagesList);
		$scope.packages = {};
		$scope.packages.file = {};
	}
	
	$scope.removeItemFromList = function(m){
		$scope.packagesList.splice(m, 1);
	}
	
	console.log($scope.categoryWisePackages);
	
	$scope.editPackages = {};
	
	$scope.editModal = function(m){
		$scope.editPackages = m;
	}
	
	$scope.updatePackages = function(packages){
		$http.post('rest/updateSinglePackagesItem',packages).success(function(data) {
        	console.log("success");
        	$scope.getExistPackagesList();
        	$scope.editPackages = {};
        	$("[data-dismiss=modal]").trigger({ type: "click" });
        }).error(function(){       	
        });
	};
	
	$scope.deletePackagesItem = function(packages){
		$http.post('rest/deleteSinglePackagesItem',packages).success(function(data) {
        	console.log("success");
        	$scope.getExistPackagesList();
        }).error(function(){       	
        });
	};
	
	$scope.addToDatabase = function(){
		
		angular.forEach($scope.packagesList,function(m){
			m.feteId = $scope.feteId;
		});
		
		angular.forEach($scope.packagesList,function(m){
			console.log(m);
			var file = m.file;
			$http.post('rest/addSinglePackagesItem',m).success(function(data) {
	        	console.log("success");
	        }).error(function(){       	
	        });
			fileUpload.uploadFileToUrl(file, 'rest/uploadPhoto');
		});
		$scope.getExistPackagesList();
		$scope.packages = {};
    	$scope.packagesList = [];
    	$("[data-dismiss=modal]").trigger({ type: "click" });
	};
	
});