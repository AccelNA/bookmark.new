/*
This handles retrieving data and is used by userController
*/
bookmarkApp.service('userService',['$http','$resource','serviceHelper', function ($http,$resource,serviceHelper)  {
var user = serviceHelper.User;
	
	 var user = {};

	 	//Registration
	 	user.signUp = function (postData) {
	 		postData.action = "login";
    	return user.register(postData).$promise;
	 	};
    	

    
    return user;

	        }]);