
  
// This handles retrieving data and is used by controllers.

bookmarkApp.service('loginService',['$http','$resource','serviceHelper', function ($http,$resource,serviceHelper) {

    var users = {};
    var $json ={};
    var user = serviceHelper.User;
   
    /*
	 * This will fetch all user details in initial time So Authentication will
	 * make minimum time
	 */

//    this.getUser = function () {
//        var users = null;
//        return users;
//
//    };

   /*
	 * Authentication operation
	 */

    users.signIn = function (userName, password) {


   var postData={
               
		   bookmark_user_email: userName,
           bookmark_user_password:password
                  };
 return user.login(postData).$promise;
      
 }

    /*
	 * Forgotpassword operation Call from logincontroller param @email return
	 * @boolean value
	 */

    this.forgotPasswordService = function (email) {

        var resetFlag = 0;

        var $json = { "results": [{    // Replace with database call & function
            "email": 'test@email.com'
        },
        {
            "email": 'test2@email.com'
        },
        {
            "email": 'test3@email.com'
        }]
        };


        $.each($json.results, function (i, item) {

            if (item.email == email) {
                console.log("The email address exist");
                // send reset link through email and reset password.
                resetFlag = 1;
                return;
            }

        });
        if (resetFlag == 0) {
            console.log("The email address doesn't exist");
        }
    };
    
    
    users.logout = function (token) {
    	

    	$http.defaults.headers.common['token']= token;
    		return user.logout.$promise;
    	 }
    
    return users;
}]);