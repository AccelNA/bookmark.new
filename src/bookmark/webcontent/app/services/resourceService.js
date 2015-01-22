/**
 * 
 */
bookmarkApp.service('resourceService', function ($http,$resource) {
	
	var resourcegetgroupname = $resource('v1/resourcegroupgetname/:bookmark_user_token');
	var resourcelist =  $resource('v1/resourceget/:bookmark_user_token');
	var activitylist =  $resource('v1/activityget/:bookmark_user_token/:bookmark_resource_name');
	var resourceadd =  $resource('v1/resourceadd');
	var removeresource = $resource ('v1/resourcedelete/:bookmark_user_token/:bookmark_resource_name/:bookmark_resource_priority')
	var editresource = $resource('v1/resourceedit',{},{'update': { method: 'PUT' }});
	var createNote = $resource('v1/addnote');
	var resource = {};

	resource.resourcegroupname = function (token) {
	   var data =resourcegetgroupname.query({bookmark_user_token: token}).$promise;
	   return data;
    };
    resource.addresource = function (postData) {
        return resourceadd.save(postData).$promise; 
    }
    
    resource.getresource = function (token) {
		   var data =resourcelist.query({bookmark_user_token: token}).$promise;
		   return data;
	    };
	    resource.editresource = function(postdata){
	 	   return editresource.update(postdata).$promise;  
	    }
	    resource.createNote = function (postData) {
	        return createNote.save(postData).$promise; 
	    }
	    
	    resource.removeresource = function(resourceName,token,resourcePriority){
	 	   return removeresource.delete({bookmark_user_token: token , bookmark_resource_name:resourceName,bookmark_resource_priority:resourcePriority}).$promise;
	    }
	    resource.getactivity = function (token,resourceName) {
			   var data =  activitylist.query({bookmark_user_token: token,bookmark_resource_name:resourceName}).$promise;
			   return data;
		    }; 
    return resource;
});