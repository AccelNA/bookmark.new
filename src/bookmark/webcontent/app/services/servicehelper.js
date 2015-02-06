/**
 * 
 */
bookmarkApp.service('serviceHelper',['$http','$resource', function ($http,$resource) {
    return {
    	BaseResources: $resource('v1/resource',{},{'update': { method: 'PUT' },
    		removeresource: {url:'v1/resourcedelete/:Resource_id', method: 'delete'}}),
    	Activity:  $resource('v1/activity',{},{byName:{url:'v1/getactivity/:bookmark_resource_name',method:'GET', isArray:true}}),
     	Resourcegroup: $resource('v1/resourcegroup',{},
    			
    			{'update': { method: 'PUT' },
    		 	 ByName: { url: 'v1/resourcegroupgetname', method: 'GET', isArray: true},
    			removegroup: {url:'v1/resourcgroupedelete/:Resourcegroup_id', method: 'delete'}	
    		}),
     
    	User: $resource('v1/user',{},
        			
        			{
        		 	 login: { url: 'v1/userlogin', method: 'POST'},
        		 	logout: { url: 'v1/userlogout', method: 'POST'},
        		    register: { url: 'v1/user', method: 'POST'}
        }),	
    	
    	UserAction : $resource('v1/user/:action', {action: '@action'}, {
            requestToken: { method: 'POST'}
        })
    	
    };
}]);
