/**
 * 
 */
bookmarkApp.controller('logoutController', [
		'$scope',
		'loginService',
		'$cookieStore',
		'$location',
		function($scope, loginService, $cookieStore,$location) {
			$scope.logout = function() {

				$scope.bookmark_user_token = $cookieStore.get("token");
				loginService.logout($scope.bookmark_user_token).then(
						function(success) {
							$scope.Logindata = {};
							$cookieStore.remove("username");
							$cookieStore.remove("token");
							$scope.errormsg = "Successfully logged out";
							 $location.path('/default');
						});
			}

			$scope.logout();

		} ]);