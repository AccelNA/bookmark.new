/**
 * 
 *//*
	 * This handles retrieving data and is used by userController
	 */
bookmarkApp.service('resourcegroupService',['$http','$resource','serviceHelper', function ($http,$resource,serviceHelper) {
   var resourcegroup  = serviceHelper.Resourcegroup;
	var resource = {};

    // Registration
	 resource.addresourcegroup = function (postData) {
     return resourcegroup.save(postData).$promise;

    }
	 resource.getGroups = function (token) {
		 $http.defaults.headers.common['token']= token;
		   var data =resourcegroup.query().$promise;
		   return data;
	    };
	    
   resource.editresourcegroup = function(postdata){
	   return resourcegroup.update(postdata).$promise;  
   }
   
   resource.removeresourcegroup = function(id,token){
	   $http.defaults.headers.common['token']= token;
	   return resourcegroup.removegroup({Resourcegroup_id:id}).$promise;
   }

    return resource;
}]);