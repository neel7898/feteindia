/**
 * 
 */
app.controller("venueController", function($scope,$location,$http) {
	console.log("VenueController");
	$scope.partyHallList = [];
	$scope.noVenueFlag = true;
	$scope.feteId = sessionStorage.getItem("feteId");
	
	$scope.setSuitableForArray =function(){
		angular.forEach($scope.partyHallList, function(v) {
			var suitableFor = v.suitableFor;
			suitableFor = suitableFor.split("~");
			var str = "";
			for(var i=0;i <= suitableFor.length;i++){
				if((suitableFor[i] != undefined) && (suitableFor[i] != "") ){
				str = str+suitableFor[i]+" | ";
				}
			}
			v.suitableFor = str;
		})
	};
	
	$scope.setFacilitiesArray = function(){
		angular.forEach($scope.partyHallList, function(v) {
			var facilities = v.facilities;
			facilities = facilities.split("~");
			var str = "";
			for(var i=0;i <= facilities.length;i++){
				if((facilities[i] != undefined) && (facilities[i] != "") ){
				str = str+facilities[i]+" | ";
				}
			}
			v.facilities = str;
		})
	}
	
	$scope.getPartyHalls = function(){
		 $http.post('rest/getPartyHalls',$scope.feteId).success(function(data){
		    	$scope.partyHallList = data;
		    	console.log($scope.partyHallList)
		    	if($scope.partyHallList.length == 0){
		    		$scope.noVenueFlag = true;
		    		}else{
		    		$scope.noVenueFlag = false;	
		    		}
		    	$scope.setSuitableForArray();
		    	$scope.setFacilitiesArray();
		    }).error(function(){})};
		    
		    
		$scope.loadAddress = function(){
			 $http({
			        url: 'rest/addressByFeteId',
			        method: 'POST',
			        headers: {
			            'Content-Type': 'application/x-www-form-urlencoded'
			        }, params: {
			            id: $scope.feteId
			        }
			    }).success(function(data){
			    	console.log(data);
			    	$scope.add = data;
			    	$scope.addressDisplay = $scope.add.location+', '+$scope.add.city+', '+$scope.add.state+', '+$scope.add.pinCode;
			    }).error(function(){});
		};    
	
		$scope.loadAddress();	
		$scope.getPartyHalls();
		
		
		$scope.tabs = [{
			tab: 1,
			selected: true,
			stepName : 'Basic Details'
		},{
			tab: 2,
			selected: false,
			stepName : 'Suitable For'
		},{
			tab: 3,
			selected: false,
			stepName : 'Facilities Available'
		},{
			tab: 4,
			selected: false,
			stepName : 'Upload Photos'
		}];
		
		$scope.resetForm = function(){
			$scope.tabs = [{
				tab: 1,
				selected: true,
				stepName : 'Basic Details'
			},{
				tab: 2,
				selected: false,
				stepName : 'Suitable For'
			},{
				tab: 3,
				selected: false,
				stepName : 'Facilities Available'
			},{
				tab: 4,
				selected: false,
				stepName : 'Upload Photos'
			}];
			$scope.stepName = 'Basic Details';
			document.getElementById('tab2').style.display = "none";
			document.getElementById('tab3').style.display = "none";
			document.getElementById('tab4').style.display = "none";
			document.getElementById('tab1').style.display = "block";
			document.getElementById('form1').reset();
		}
		
		$scope.resetForm();
		
		$scope.changeTab = function(curTab,nexTab){
			var curTabId = "tab"+curTab;
			var nexTabId = "tab"+nexTab;
			$scope.stepName = $scope.tabs[nexTab-1].stepName;
			document.getElementById(curTabId).style.display = "none";
			document.getElementById(nexTabId).style.display = "block";	
		}
		
		
		
		$scope.next = function(){
			var currentTab = "";
			var nextTab = "";
			angular.forEach($scope.tabs, function(t) {
				if(t.selected == true){
					currentTab = t.tab;
					t.selected = false;
				}
			});
			
			if(currentTab < $scope.tabs.length){
				nextTab = currentTab+1;
				$scope.tabs[nextTab-1].selected = true;
			}
			console.log($scope.tabs);
			$scope.changeTab(currentTab, nextTab);
		}
		
		$scope.previous = function(){
			var currentTab = "";
			var prevTab = "";
			angular.forEach($scope.tabs, function(t) {
				if(t.selected == true){
					currentTab = t.tab;
					t.selected = false;
				}
			});
			
			if(currentTab <= $scope.tabs.length){
				prevTab = currentTab-1;
				$scope.tabs[prevTab-1].selected = true;
			}
		$scope.changeTab(currentTab,prevTab);
		}
			
		$scope.deleteVenue = function(id){
			var conf = confirm("Do you really want to delete selected venue?")
			if(conf){
			$http({
		        url: 'rest/deleteVenueByID',
		        method: 'POST',
		        headers: {
		            'Content-Type': 'application/x-www-form-urlencoded'
		        }, params: {
		            id: id
		        }
		    }).success(function(data){
		    	$scope.getPartyHalls();
		    }).error(function(){});}
		}
		
		$(function () {
		    $("#fileupload").change(function () {
		        if (typeof (FileReader) != "undefined") {
		            var dvPreview = $("#dvPreview");
		            dvPreview.html("");
		            var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.jpg|.jpeg|.gif|.png|.bmp)$/;
		            $($(this)[0].files).each(function () {
		                var file = $(this);
		                if (regex.test(file[0].name.toLowerCase())) {
		                    var reader = new FileReader();
		                    reader.onload = function (e) {
		                        var img = $("<img />");
		                        img.attr("style", "width: 150px;margin-left: 10px;margin-right:10px;");
		                        img.attr("src", e.target.result);
		                        dvPreview.append(img);
		                    }
		                    reader.readAsDataURL(file[0]);
		                } else {
		                    alert(file[0].name + " is not a valid image file.");
		                    dvPreview.html("");
		                    return false;
		                }
		            });
		        } else {
		            alert("This browser does not support HTML5 FileReader.");
		        }
		    });
		});
		
		
})