
// This handles retrieving data and is used by controllers.

bookmarkApp.service('loginService', [ '$http', '$resource', 'serviceHelper',
		function($http, $resource, serviceHelper) {

			var users = {};
			var $json = {};
			var user = serviceHelper.User;

			/*
			 * Authentication operation
			 */

			users.signIn = function(userName, password) {

				var postData = {

					bookmark_user_email : userName,
					bookmark_user_password : password
				};
				return user.login(postData).$promise;

			}

			users.logout = function(token) {

				$http.defaults.headers.common['token'] = token;
				return user.logout.$promise;
			}

			return users;
		} ]);