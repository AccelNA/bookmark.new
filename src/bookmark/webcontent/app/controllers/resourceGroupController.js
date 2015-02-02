/**
 * 
 */
/*
This controller retrieves data from the resource group service
*/
bookmarkApp.controller('resourceGroupController',['$scope','$routeParams','resourcegroupService','$cookieStore','$location','ngTableParams','$filter', function ($scope, $routeParams, resourcegroupService,$cookieStore,$location,ngTableParams,$filter) {

	
	$scope.userData = {};
	var selectedAdvmt = $routeParams.name;
	var descriptionData = $routeParams.description;
	

    $scope.fnAddresourcegroup = function (isValid) {
        if (isValid) {
        	
        	$scope.userData.bookmark_user_token =$cookieStore.get("token");
        	resourcegroupService.addresourcegroup($scope.userData).then(function(success){
        	$scope.userData = {};
        	$scope.grouplist();
        	$location.path('/resourcegroup');
        	$scope.successmsg = "Successfully added the resource group";
           	$scope.resourcegroupForm.$setPristine();
        	  },function(error) {
        	    	$scope.showWait = false;
        	        $scope.errormsg = "The Resource Group is not added due to some network issues";
        	});    
        	}    
        };
	    //Edit group
	    $scope.fnEditgroup = function (groups) {
	    	$scope.groupdata = groups;
	    	$scope.Bookmark_resourcegroup_name = groups.groupName;
	    	$scope.Bookmark_resourcegroup_decription = groups.groupDescription ? groups.groupDescription: null;
	    	$location.path('/resourcegroupedit/'+$scope.Bookmark_resourcegroup_name+"/"+$scope.Bookmark_resourcegroup_decription);
   
	    };



//
    //Delete Group
    $scope.fnRemoveResourceGroup = function (groupName) {
    	$scope.groupName =groupName
    	$scope.token = $cookieStore.get("token");
        result = resourcegroupService.removeresourcegroup($scope.groupName,$scope.token).then(function(success){
        	$scope.grouplist();
        	$location.path('/resourcegroup');
        	$scope.successmsg = "Successfully Removed the resource group";
           	$scope.resourcegroupForm.$setPristine();
            $scope.grouplist();
        	  },function(error) {
        	    	$scope.showWait = false;
        	        $scope.errormsg = "The Resource Group is not removed due to some network issues";
        	});    
        	} ;   



// update group
	    $scope.initUpdate = function () {
	    	$scope.nm = selectedAdvmt;
	    	if("null" !== descriptionData) {
	    		$scope.desc = descriptionData;
	    	}else {
	    		$scope.desc = "";
	    	}
	    };
	    
	    $scope.updateVal = function () {
	    	$scope.postdata = {};
	    	$scope.postdata.Bookmark_resourcegroup_name = $scope.nm;
	    	$scope.postdata.Bookmark_resourcegroup_decription = $scope.desc
	    	$scope.token = $cookieStore.get("token");
	    	$scope.postdata.bookmark_user_token = $scope.token;
	    	resourcegroupService.editresourcegroup($scope.postdata).then(function(success){
        		$scope.userData = {};
        		$scope.grouplist();
        		 $location.path('/resourcegroup');
        		$scope.successmsg = "Successfully updated the resource";
           	    $scope.resourceForm.$setPristine();
        	  },function(error) {
        	    	$scope.showWait = false;
        	        $scope.errormsg = "The Resource  is not updated due to some network issues";
        	}); 
	
	    };
	    
//grouplist
	    
        $scope.grouplist = function () {
        	$scope.token = $cookieStore.get("token");
            resourcegroupService.getGroups($scope.token).then(function (respData){
            $scope.tabledata = respData;
            
	            $scope.tableParams = new ngTableParams({
	                page: 1,              // show first page
	                count: 100,
	                sorting: {name: 'asc'}// initial sorting
	            },
	            {
	                total: $scope.tabledata.length, // length of data
	                getData: function ($defer, params) {
	                    var orderedData = params.sorting() ? $filter('orderBy')($scope.tabledata, params.orderBy()) : $scope.tabledata;
	                    var orderedData = params.filter() ? $filter('filter')(orderedData, params.filter()) : $scope.tabledata;
	                    $scope.groups = orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count());
	
	                    params.total(orderedData.length); // set total for recalc pagination
	                    $defer.resolve($scope.groups);
	               
	                }
	            });
       
            });
        };
}]);