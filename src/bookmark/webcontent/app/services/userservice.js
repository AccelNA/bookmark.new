/*
This handles retrieving data and is used by userController
*/
bookmarkApp.service('userService', function ($http,$resource) {

	var register = $resource('v1/user');
	 var user = {};

	 	//Registration
	 	user.signUp = function (postData) {
    	return register.save(postData).$promise;

    	
    	
    	
    	
//    	return $http({
//            method: "post",
//            url: 'v1/user',
//            //params: {},
//           data: postData
//        });
//         

//        console.log(data);
//        //Server Communication gives out put with type(success/error/danger) and message
//        var $result = [{
//            "type": "success",
//            "msg": "You have been registered successfully."
//        }];
//        return $result;
    }

//    //Add User
//    user.addUser = function (postData) {
//        //Server Communication gives out put with type(success/error/danger) and message
//        var $result = [{
//            "type": "success",
//            "msg": "User been added successfully."
//        }];
//        return $result;
//    }
//    //Add User
//    user.updateUser = function (postData) {
//        //Server Communication gives out put with type(success/error/danger) and message
//        var $result = [{
//            "type": "success",
//            "msg": "User been updated successfully."
//        }];
//        return $result;
//    }
//    //Remove User
//    user.removeUser = function (userid) {
//        //Server Communication gives out put with type(success/error/danger) and message
//        var $result = [{
//            "type": "success",
//            "msg": "User been removed successfully."
//        }];
//        return $result;
//    }
//
//    //Get User List
//    user.getUsers = function () {
//        var data = [{ id: 1, name: "Moroni", age: 50, emailId: "emailId@emailId.com", address: "Address1" },  // The userList data will get frim database
//                {id: 2, name: "Tiancum", age: 43, emailId: "emailId@emailId.com", address: "Address2" }, // change this code to database fetch function
//                {id: 3, name: "Jacob", age: 27, emailId: "emailId@emailId.com", address: "Address3" },
//                { id: 4, name: "Nephi", age: 29, emailId: "emailId@emailId.com", address: "Address4" },
//                { id: 5, name: "Enos", age: 34, emailId: "emailId@emailId.com", address: "Address5" },
//                { id: 6, name: "Tiancum", age: 43, emailId: "emailId@emailId.com", address: "Address5" },
//                { id: 7, name: "Jacob", age: 27, emailId: "emailId@emailId.com", address: "Address6" },
//                { id: 8, name: "Nephi", age: 29, emailId: "emailId@emailId.com", address: "Address7" },
//                { id: 9, name: "Enos", age: 34, emailId: "emailId@emailId.com", address: "Address8" },
//                { id: 10, name: "Tiancum", age: 43, emailId: "emailId@emailId.com", address: "Address9" },
//                { id: 11, name: "Jacob", age: 27, emailId: "emailId@emailId.com", address: "Address0" },
//                { id: 12, name: "Nephi", age: 29, emailId: "emailId@emailId.com", address: "Address1" },
//                { id: 13, name: "Enos", age: 34, emailId: "emailId@emailId.com", address: "Address1" },
//                { id: 14, name: "Tiancum", age: 43, emailId: "emailId@emailId.com", address: "Address1" },
//                { id: 15, name: "Jacob", age: 27, emailId: "emailId@emailId.com", address: "Address1" },
//                { id: 16, name: "Nephi", age: 29, emailId: "emailId@emailId.com", address: "Address1" },
//                { id: 17, name: "Enos", age: 34, emailId: "emailId@emailId.com", address: "Address1"}];
//
//
//        return data;
//    }
//
//    //Change Password
//    user.changePassword = function (postData) {
//        console.log(postData);
//        //Server Communication
//        var $result = [{
//            "type": "error",
//            "msg": "You entered invalid old password."
//        }];
//        return $result;
//    }
    
    return user;
});