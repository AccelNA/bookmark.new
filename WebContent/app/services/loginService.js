
  
// This handles retrieving data and is used by controllers.

bookmarkApp.service('loginService', function ($http,$resource) {

    var users = {};
    var $json ={};
    /*
	 * This will fetch all user details in initial time So Authentication will
	 * make minimum time
	 */

    this.getUser = function () {
        var users = null;
        return users;

    };

   /*
	 * Authentication operation
	 */

    this.signIn = function (userName, password) {


   var postData={
               
		   bookmark_user_email: userName,
           bookmark_user_password:password
                  };

	
	return $http({
		method:"post",
		url:'v1/userlogin',
		params:{},
		data:postData

	});
  
      
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
    this.logout = function (token) {

    	var postData={
    	           bookmark_user_token:token
    	                  };
    	   
    		return $http({
    			method:"post",
    			url:'v1/userlogout',
    			params:{},
    			data:postData
    		});
    	  
    	      
    	 }
});