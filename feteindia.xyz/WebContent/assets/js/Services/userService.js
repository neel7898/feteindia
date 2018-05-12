app.factory('userService', function($http) {
	
	var feteId = sessionStorage.getItem("feteId");
	
	return {

		loadAccountDetails : function(){},
		
		getExistMenuList : function(){
			$http.post('../rest/getMenuListByFeteId',feteId).success(function(data) {
				console.log(data);
				if(data.length != 0){
					try{
					if(data.message != "error"){	
					return data;
					}else{
						return "{message : error}";
					}
					}catch(err){
						
					}
				}
			}).error(function(){
				console.log("some error in fetching");
				return "{message : error}";
			})
		},
		
		getExistPackagesList : function(){
			$http.post('../rest/getPackagesListByFeteId',$scope.feteId).success(function(data) {
				console.log(data);
				if(data.length != 0){
					try{
					if(data.message != "error"){	
					return data;
					}else{
						return "{message : error}";
					}
					}catch(err){
						
					}
				}
			}).error(function(){
				console.log("some error in fetching");
				return "{message : error}";
			})
		},
		
		getPartyHalls : function(){
			 $http.post('../rest/getPartyHalls',feteId).success(function(data){
			  
				 if(data.length != 0){
						try{
						if(data.message != "error"){	
						return data;
						}else{
							return "{message : error}";
						}
						}catch(err){
							
						}
					}
			    	
			    }).error(function(){
			    	console.log("some error in fetching");
					return "{message : error}";
			    })
		}
		
		/*getAllUsers : function() {
			return $http.post('rest/hello');
		},

		updateEmployee : function(emp) {
			return $http.post('rest/updateEmployee', emp);
		}*/
	};
});

