/*
This controller retrieves data from the userService and associates it with the $scope
*/
bookmarkApp.controller('resourceController',['$scope','$routeParams','resourceService', '$cookieStore','$location','$http','$resource','ngTableParams','$filter', function ($scope,$routeParams,resourceService, $cookieStore,$location, $http, $resource,ngTableParams,$filter) {
	$scope.options = ["1-high", "2-medium","3-low"];
	$scope.selectedOption={};
	$scope.userData = {};
	$scope.token = $cookieStore.get("token");
    $scope.selected ={};  
	$scope.priority={};
	var resourceName = $routeParams.name;
	var descriptionData = $routeParams.description;
	//var path = $routeParams.path;
	var selectedpriority = $routeParams.selectedpriority;
	var groupname = $routeParams.groupname;
	var rname =$routeParams.resourcename;
	
	$scope.getgroupname = function(){
	  resourceService.resourcegroupname($scope.token).then(function (respData) {
		$scope.data = respData;
		$scope.getresource();
	  });
	//  var x =0;
	};
    $scope.getresource = function () {
    	$scope.token = $cookieStore.get("token");
    	resourceService.getresource($scope.token).then(function (respData){
        $scope.tabledata = respData;
        
            $scope.tableParams = new ngTableParams({
                page: 1,              // show first page
                count: 100,
                sorting: {name: 'asc'}// initial sorting
            },
            {
                total: $scope.tabledata.length, // length of data
                getData: function ($defer, params) {
                    // use build-in angular filtermain
                    // debugger;
                    var orderedData = params.sorting() ? $filter('orderBy')($scope.tabledata, params.orderBy()) : $scope.tabledata;
                    var orderedData = params.filter() ? $filter('filter')(orderedData, params.filter()) : $scope.tabledata;
                    $scope.resource = orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count());

                    params.total(orderedData.length); // set total for recalc pagination
                    $defer.resolve($scope.resource);
               
                }
            });
   
        });
    };

    //Add Resource
    $scope.fnAddresource = function (isValid) {
        if (isValid) {
        	
        	$scope.userData.bookmark_user_token =$cookieStore.get("token");
        	$scope.userData.Bookmark_resourcegroup_name = $scope.selected;
        	$scope.userData.Bookmark_resource_priority =$scope.priority;
        	resourceService.addresource($scope.userData).then(function(success){
        		$scope.userData = {};
        		 $scope.getresource();
        		 $location.path('/resource');
        		$scope.successmsg = "Successfully added the resource";
           	    $scope.resourceForm.$setPristine();
        	  },function(error) {
        	    	$scope.showWait = false;
        	        $scope.errormsg = "The Resource  is not added due to some network issues";
        	});    
        	}    
        };
        
        $scope.fnEditresource = function (resource) {
        	alert("------Vidya---"+resource.resourceName+"--Test---"+resource.resourcePath+"===="+resource.resourcePriority+"\\\\"+resource.resourceGroupName+"......."+resource.resourceDescription);
	    	$scope.resourcedata = resource;
	    	$scope.Bookmark_resource_name = resource.resourceName;
	    	$scope.Bookmark_resource_decription = resource.resourceDescription ? resource.resourceDescription: null;
	    	$scope.Bookmark_resource_path = resource.resourcePath;
	    	$scope.Bookmark_resource_priority = resource.resourcePriority;
	    	$scope.Bookmark_resourcegroup_name = resource.resourceGroupName;
	    	$cookieStore.put('path', resource.resourcePath);
	    	$location.path('/resourceedit/'+$scope.Bookmark_resource_name+"/"+$scope.Bookmark_resource_decription+"/"+$scope.Bookmark_resource_priority+"/"+$scope.Bookmark_resourcegroup_name);
	    	var x = 0;
	    };
	    $scope.initupdateresource = function () {
	    	$scope.resourcename = resourceName;
	    	$scope.resourcepath = $cookieStore.get("path");
	    	$scope.resourcepriority = selectedpriority;
	    	$scope.resourcegroupname = groupname;
	    	if("null" !== descriptionData) {
	    		$scope.resourcedescription = descriptionData;
	    	}else {
	    		$scope.resourcedescription = "";
	    	}
	    	$scope.getgroupname();
	    };
	    
	    
	    $scope.fnUpdateresource = function () {
	    	alert("------Vidya---"+$scope.resourcename+"--Test---"+$scope.resourcepath+"===="+$scope.resourcepriority+"\\\\"+$scope.resourcegroupname+"......."+$scope.resourcedescription);
	    	$scope.postdata = {};
	    	$scope.postdata.Bookmark_resource_name = $scope.resourcename;
	    	$scope.postdata.Bookmark_resource_decription = $scope.resourcedescription;
	    	$scope.postdata.Bookmark_resource_path = $cookieStore.get("path");
	    	$scope.postdata.Bookmark_resource_priority = $scope.resourcepriority;
	    	$scope.postdata.Bookmark_resourcegroup_name = $scope.resourcegroupname;
	    	$scope.token = $cookieStore.get("token");
	    	$scope.postdata.bookmark_user_token = $scope.token;
	    	
	    	resourceService.editresource($scope.postdata).then(function(success){
        		$scope.userData = {};
        		 $scope.getresource();
        		 $location.path('/resource');
        		$scope.successmsg = "Successfully updated the resource";
           	    $scope.resourceForm.$setPristine();
        	  },function(error) {
        	    	$scope.showWait = false;
        	        $scope.errormsg = "The Resource  is not updated due to some network issues";
        	}); 
	    	
	    };
	    
	    $scope.fnAddNotes =function(resourcename){
	    	$location.path('/addnotes/'+resourcename);
	    	
	    }
	    $scope.initnotes =function(){
	    	$scope.resourcename = rname;
	    	 $scope.getactivity();
	    	var x=0;
	    }
	    $scope.fncreateNote = function (isValid) {
	        if (isValid) {
	        	$scope.userData.Bookmark_activity_Resource_name =rname;
	        	$scope.userData.bookmark_user_token =$cookieStore.get("token");
	        	resourceService.createNote($scope.userData).then(function(success){
	        		$scope.userData = {};
	        		$location.path('/addnotes/'+rname);
	        		$scope.successmsg = "Successfully added the Note";
	           	    $scope.resourceForm.$setPristine();
	        	  },function(error) {
	        	    	$scope.showWait = false;
	        	        $scope.errormsg = "The Resource  is not added due to some network issues";
	        	});    
	        	}    
	        };
	        $scope.fnRemoveResource = function (resource) {
	        	$scope.resourceName =resource.resourceName;
	        	$scope.resourcePriority = resource.resourcePriority;
	        	$scope.token = $cookieStore.get("token");
	            result = resourceService.removeresource($scope.resourceName,$scope.token,$scope.resourcePriority).then(function(success){
	            	$scope.getresource();
	            	$location.path('/resource');
	            	$scope.successmsg = "Successfully Removed the resource ";
	               	$scope.resourceForm.$setPristine();
	            	  },function(error) {
	            	    	$scope.showWait = false;
	            	        $scope.errormsg = "The Resource Group is not removed due to some network issues";
	            	});    
	            	} ;   
	            	
	            	 $scope.getactivity = function () {
	            		 $scope.resourceName = rname
	            	    	$scope.token = $cookieStore.get("token");
	            	    	resourceService.getactivity($scope.token, $scope.resourceName).then(function (respData){
	            	        $scope.tabledata = respData;
	            	        
	            	            $scope.tableParams = new ngTableParams({
	            	                page: 1,              // show first page
	            	                count: 100,
	            	                sorting: {name: 'asc'}// initial sorting
	            	            },
	            	            {
	            	                total: $scope.tabledata.length, // length of data
	            	                getData: function ($defer, params) {
	            	                    // use build-in angular filtermain
	            	                    // debugger;
	            	                    var orderedData = params.sorting() ? $filter('orderBy')($scope.tabledata, params.orderBy()) : $scope.tabledata;
	            	                    var orderedData = params.filter() ? $filter('filter')(orderedData, params.filter()) : $scope.tabledata;
	            	                    $scope.notes = orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count());

	            	                    params.total(orderedData.length); // set total for recalc pagination
	            	                    $defer.resolve($scope.notes);
	            	               
	            	                }
	            	            });
	            	   
	            	        });
	            	    };
	            
}]);