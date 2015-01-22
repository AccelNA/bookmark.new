
'use strict';
/*#######################################################################
  
  @Product Name : Accel Coutinum
  @Date         : 16-10-2014

  #######################################################################*/


//This controller Retrive the data afrom user table and use for authentication purpose
//The $scope is ultimately bound to the authentication
bookmarkApp.controller('loginController',['$scope','loginService', '$cookieStore','$location', function ( $scope, loginService, $cookieStore,$location) {

    //An init() for controllers that need to perform some initialization.
	//$rootscope.hide=true;
    $scope.Logindata = {};

    $scope.clearCookies = function () {
 	   $cookieStore.remove("username");
 		$cookieStore.remove("token");
    };
    
    init();

    function init() {
    	$scope.clearCookies();
        $scope.Logindata.bookmark_user_email   =  "";
        $scope.Logindata.bookmark_user_password   =  "";
        $scope.users = loginService.getUser();
    }
    
    $scope.loginuser = function (isValid) {

        // Two parameters passing as Authdetails ; 
        if (isValid) {


             var result = loginService.signIn($scope.Logindata.bookmark_user_email,$scope.Logindata.bookmark_user_password).
            then(function(success){
            	
            	 alert(success.data.bookmark_user_token);
            	 $cookieStore.put('token', success.data.bookmark_user_token);
            	 $cookieStore.put('email', success.data.bookmark_user_email);
            	 $location.path('/resourcehome');
 
    },function(error) {
    	$scope.showWait = false;
        $scope.errormsg = "Invalid Username or password";
});      

//            if ($scope.Logindata.remeberMe == true) {
//                ipCookie('username', $scope.Logindata.username);
//                ipCookie('password', $scope.Logindata.password);
//            }
//            $scope.Logindata.username = null;
//            $scope.Logindata.password = null;
//        }
           
       

    };
    
    };

    /*
    * Forgot pasword controller code .
    * Calling comes from forgotpassword.html page
    */

    $scope.forgotPassword = function (isValid) {

        //One parameter is Email
        if (isValid) {
            var userForgotPassword = loginService.forgotPasswordService($scope.Logindata.userEmail);
        }
    };
}]);