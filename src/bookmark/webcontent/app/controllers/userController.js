/*
This controller retrieves data from the userService and associates it with the $scope
 */
bookmarkApp
		.controller(
				'userController',
				[
						'$scope',
						'userService',
						'loginService',
						'$cookieStore',
						'$location',
						function($scope, userService, loginService,
								$cookieStore, $location) {

							$scope.signupData = {};
							$scope.changePassData = {};
							$scope.userData = {}
							$scope.Logindata = {};

							$scope.clearCookies = function() {
								$cookieStore.remove("bookmark_user_email");
								$cookieStore.remove("token");
							};

							init();

							function init() {
								$scope.clearCookies();
								$scope.Logindata.bookmark_user_email = "";
								$scope.Logindata.bookmark_user_password = "";
							}

							// Registration
							$scope.fnSignup = function(isValid) {
								if (isValid) {
									result = userService
											.signUp($scope.signupData)
											.then(
													function(result) {

														$scope.signupData = {};
														$scope.successmsg = "Hi you have Succesfully Registered";
														$scope.signupForm
																.$setPristine();
													});
								}
							};

							$scope.loginuser = function(isValid) {

								// Two parameters passing as Authdetails ;
								if (isValid) {

									var result = loginService
											.signIn(
													$scope.Logindata.bookmark_user_email,
													$scope.Logindata.bookmark_user_password)
											.then(
													function(success) {
														$cookieStore
																.put(
																		'token',
																		success.bookmark_user_token);
														$location
																.path('/resourcehome');

													},
													function(error) {
														$scope.showWait = false;
														$scope.errormsg = "Invalid Username or password";
													});

								}
								;

							};

							$scope.logout = function() {

								$scope.bookmark_user_token = $cookieStore
										.get("token");
								loginService
										.logout($scope.bookmark_user_token)
										.then(
												function(success) {
													$scope.Logindata = {};
													$cookieStore
															.remove("token");
													$scope.errormsg = "Successfully logged out";
													$location.path('/default');
												});
							}

						} ]);