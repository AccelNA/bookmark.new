/*
This controller retrieves data from the userService and associates it with the $scope
*/
bookmarkApp.controller('userController', function ($scope, userService, $http, $resource,ngTableParams, $filter) {

    $scope.signupData = {};
    $scope.changePassData = {};
    $scope.userData = {}

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

    //Change Password
    $scope.fnChnagePassword = function (isValid) {
        if (isValid) {
            result = userService.changePassword($scope.changePassData);
            $scope.alerts = result;
        }
    };

    //Close Alert box
    $scope.closeAlert = function (index) {
        $scope.alerts.splice(index, 1);
    };


    //Add User
    $scope.fnAdduser = function (isValid) {
        if (isValid) {
            if ($scope.userData.id > 0)
                result = userService.updateUser($scope.userData);
            else {
                data.push($scope.userData);
                result = userService.addUser($scope.userData);
            }


            $scope.alerts = result;
        }

    };

    //Edit User
    $scope.fnEdituser = function (userData) {
        $scope.userData = userData;

    };

    //Delete User
    $scope.fnRemoveUser = function (userid) {
        result = userService.removeUser(userid);
        $scope.alerts = result;
    }

    /*
    * All User List should be shwoing here
    * /view/userList.html file is using
    */

    $scope.userList = function () {
        data = userService.getUsers();
        $scope.tableParams = new ngTableParams({
            page: 1,            // show first page
            count: 100,

            sorting: {
                name: 'asc'     // initial sorting
            }
        },
        {
            total: data.length, // length of data
            getData: function ($defer, params) {
                // use build-in angular filtermain
                
                var orderedData = params.sorting() ? $filter('orderBy')(data, params.orderBy()) : data;
                var orderedData = params.filter() ? $filter('filter')(orderedData, params.filter()) : data;
                $scope.users = orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count());

                params.total(orderedData.length); // set total for recalc pagination
                $defer.resolve($scope.users);
            }
        });
    };
});