/**
 * 
 */
bookmarkApp.service('resourceService',['$http','$resource','serviceHelper', function ($http,$resource,serviceHelper) {
	var baseResource = serviceHelper.BaseResources;
	var note = serviceHelper.Activity;
	var Resourcegroup = serviceHelper.Resourcegroup;
			
	var resource = {};

	resource.resourcegroupname = function (token) {
		  $http.defaults.headers.common['token']= token;
		  var data = Resourcegroup.ByName().$promise;
	   return data;
    };

    resource.addresource = function (postData) {
        return baseResource.save(postData).$promise; 
    }
    
    resource.getresource = function (token) {
    	 $http.defaults.headers.common['token']= token;
		   var data =baseResource.query().$promise;
		   return data;
	    };
	    resource.editresource = function(postdata){
	 	   return baseResource.update(postdata).$promise;  
	    }
	    resource.createNote = function (postData) {
	        return note.save(postData).$promise; 
	    }
	    
	    resource.removeresource = function(id,token){
	    	$http.defaults.headers.common['token']= token;
	 	   return baseResource.removeresource({Resource_id: id}).$promise;
	    }
	    resource.getactivity = function (token,resourceName) {
	    	$http.defaults.headers.common['token']= token;
			   var data =  note.byName({bookmark_resource_name: resourceName}).$promise;
			   return data;
		    }; 
    return resource;
}]);