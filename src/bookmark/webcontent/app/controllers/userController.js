/*
This controller retrieves data from the userService and associates it with the $scope
*/
bookmarkApp.controller('userController',['$scope','userService','loginService', '$cookieStore','$location', function ($scope, userService,loginService, $cookieStore, $location) {

    $scope.signupData = {};
    $scope.changePassData = {};
    $scope.userData = {}
    $scope.Logindata = {};

    
    $scope.clearCookies = function () {
  	   $cookieStore.remove("bookmark_user_email");
  		$cookieStore.remove("token");
     };
     
     init();

     function init() {
     	$scope.clearCookies();
         $scope.Logindata.bookmark_user_email   =  "";
         $scope.Logindata.bookmark_user_password   =  "";
        // $scope.users = loginService.getUser(); TODO for test
     }
    //Registration
    $scope.fnSignup = function (isValid) {
        if (isValid) {
            result = userService.signUp($scope.signupData).then(function(result){
            	
            	$scope.signupData = {};
            	$scope.successmsg = "Hi you have Succesfully Registered";
            	 $scope.signupForm.$setPristine();
            });       
        }
    };


  
    
    $scope.loginuser = function (isValid) {

        // Two parameters passing as Authdetails ; 
        if (isValid) {


             var result = loginService.signIn($scope.Logindata.bookmark_user_email,$scope.Logindata.bookmark_user_password).
            then(function(success){
            	
            	 alert(success.bookmark_user_token);
            	 $cookieStore.put('token', success.bookmark_user_token);
            	 //$cookieStore.put('email', success.data.bookmark_user_email);
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

	$scope.logout = function() {

		$scope.bookmark_user_token = $cookieStore.get("token");
		loginService.logout($scope.bookmark_user_token).then(
				function(success) {
					$scope.Logindata = {};
				//	$cookieStore.remove("username");
					$cookieStore.remove("token");
					$scope.errormsg = "Successfully logged out";
					 $location.path('/default');
				});
	}

//	$scope.logout();
//    //Change Password
//    $scope.fnChnagePassword = function (isValid) {
//        if (isValid) {
//            result = userService.changePassword($scope.changePassData);
//            $scope.alerts = result;
//        }
//    };
//
//    //Close Alert box
//    $scope.closeAlert = function (index) {
//        $scope.alerts.splice(index, 1);
//    };
//
//
//    //Add User
//    $scope.fnAdduser = function (isValid) {
//        if (isValid) {
//            if ($scope.userData.id > 0)
//                result = userService.updateUser($scope.userData);
//            else {
//                data.push($scope.userData);
//                result = userService.addUser($scope.userData);
//            }
//
//
//            $scope.alerts = result;
//        }
//
//    };
//
//    //Edit User
//    $scope.fnEdituser = function (userData) {
//        $scope.userData = userData;
//
//    };
//
//    //Delete User
//    $scope.fnRemoveUser = function (userid) {
//        result = userService.removeUser(userid);
//        $scope.alerts = result;
//    }
//
//    /*
//    * All User List should be shwoing here
//    * /view/userList.html file is using
//    */
//
//    $scope.userList = function () {
//        data = userService.getUsers();
//        $scope.tableParams = new ngTableParams({
//            page: 1,            // show first page
//            count: 100,
//
//            sorting: {
//                name: 'asc'     // initial sorting
//            }
//        },
//        {
//            total: data.length, // length of data
//            getData: function ($defer, params) {
//                // use build-in angular filtermain
//                
//                var orderedData = params.sorting() ? $filter('orderBy')(data, params.orderBy()) : data;
//                var orderedData = params.filter() ? $filter('filter')(orderedData, params.filter()) : data;
//                $scope.users = orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count());
//
//                params.total(orderedData.length); // set total for recalc pagination
//                $defer.resolve($scope.users);
//            }
//        });
//    };
}]);