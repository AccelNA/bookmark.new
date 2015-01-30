/**
 * 
 */
bookmarkApp.service('serviceHelper',['$http','$resource', function ($http,$resource) {
    return {
    	BaseResources: $resource('v1/resource',{},{'update': { method: 'PUT' }}),
    	Activity:  $resource('v1/activity'),
    	Resourcegroup: $resource('v1/resourcegroup',{},
    			
    			{'update': { method: 'PUT' },
    		 	 ByName: { url: 'v1/resourcegroupgetname', method: 'GET', isArray: true}
    		
    		}),
     
    	User: $resource('v1/user',{},
        			
        			{
        		 	 login: { url: 'v1/userlogin', method: 'POST'},
        		 	logout: { url: 'v1/userlogout', method: 'POST'},
        		    register: { url: 'v1/user', method: 'POST'}
        }),	
//    	Login: $resource('v1/userlogin'),
//    	Logout: $resource('v1/userlogout'),
//    	 register = $resource('v1/user')
    	
    	UserAction : $resource('v1/user/:action', {action: '@action'}, {
            requestToken: { method: 'POST'}
        })
    	
    };
}]);
