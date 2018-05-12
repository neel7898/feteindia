/**
 * 
 */
app.controller("menuController",function($scope,$http,fileUpload) {
	console.log('menu controller loaded');
	$scope.feteId = sessionStorage.getItem("feteId");
	$scope.noMenuFlag = true;
	$scope.menuList = [];
	
	$scope.existMenuList = [];
	
	$scope.getExistMenuList = function(){
		$http.post('rest/getMenuListByFeteId',$scope.feteId).success(function(data) {
			console.log(data);
			$scope.existMenuList = data;
			if($scope.existMenuList.length != 0){
				try{
				if($scope.existMenuList.message != "error"){	
				$scope.noMenuFlag = false;
				}else{
					$scope.noMenuFlag = true;
				}
				}catch(err){
					
				}
			}
		}).error(function(){
			console.log("some error");
		})
	}
	
	$scope.getExistMenuList();
	
	$scope.addToMenuList = function(menu){
		$scope.menuList.push(menu);
		console.log($scope.menuList);
		$scope.menu = {};
		$scope.menu.file = {};
	}
	
	$scope.removeItemFromList = function(m){
		$scope.menuList.splice(m, 1);
	}
	
	console.log($scope.categoryWiseMenu);
	
	$scope.editMenu = {};
	
	$scope.editModal = function(m){
		$scope.editMenu = m;
	}
	
	$scope.updateMenu = function(menu){
		$http.post('rest/updateSingleMenuItem',menu).success(function(data) {
        	console.log("success");
        	$scope.getExistMenuList();
        	$scope.editMenu = {};
        	$("[data-dismiss=modal]").trigger({ type: "click" });
        }).error(function(){       	
        });
	};
	
	$scope.deleteMenuItem = function(menu){
		$http.post('rest/deleteSingleMenuItem',menu).success(function(data) {
        	console.log("success");
        	$scope.getExistMenuList();
        }).error(function(){       	
        });
	};
	
	$scope.addToDatabase = function(){
		
		angular.forEach($scope.menuList,function(m){
			m.feteId = $scope.feteId;
		});
		
		angular.forEach($scope.menuList,function(m){
			console.log(m);
			var file = m.file;
			$http.post('rest/addSingleMenuItem',m).success(function(data) {
	        	console.log("success");
	        }).error(function(){       	
	        });
			fileUpload.uploadFileToUrl(file, 'rest/uploadPhoto');
		});
		$scope.getExistMenuList();
		$scope.menu = {};
    	$scope.menuList = [];
    	$("[data-dismiss=modal]").trigger({ type: "click" });
	};
	
});