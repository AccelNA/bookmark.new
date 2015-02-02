var bookmarkApp = angular.module('bookmarkApp', ['ngRoute','ngCookies','ngTable','ui.bootstrap','ngResource']);


function homeController($scope) {
    
};


bookmarkApp.config(function ($routeProvider,$httpProvider) {

    $httpProvider.defaults.withCredentials = false;
    $httpProvider.defaults.useXDomain = true;

    $routeProvider

    .when('/', {
        templateUrl: 'app/views/default.html',
        controller: 'userController'
    })
    .when('/signin', {
        templateUrl: 'app/views/default.html',
        controller: 'userController'
    })
    .when('/signup', {
        templateUrl : 'app/views/signup.html',
        controller  : 'userController'
    })
    .when('/forgotpassword', {
        templateUrl: 'app/views/forgotpassword.html',
        controller : 'userController'
    })
   .when('/userlist', {
        templateUrl: 'app/views/userlist.html',
        controller : 'userController'
    })
    .when('/changepassword', {
        templateUrl : 'app/views/changePassword.html',
        controller  : 'userController'
    })
    .when('/logout', {
        templateUrl : 'app/views/login.html',
        controller  : 'userController'
    })
   .when('/resourcehome', {
        templateUrl : 'app/views/resourcehome.html',
        controller  : 'resourceController'
    })
    .when('/resourcegroup', {
        templateUrl : 'app/views/resourcegrouphome.html',
        controller  : 'resourceGroupController'
    })
    .when('/resourcegroupedit/:name/:description', {
        templateUrl : 'app/views/resourcegroupedit.html',
        controller  : 'resourceGroupController'
    })
    .when('/resourceedit/:name/:description/:selectedpriority/:groupname', {
        templateUrl : 'app/views/resourceedit.html',
        controller  : 'resourceController'
    })
     .when('/resource', {
        templateUrl : 'app/views/resource.html',
        controller  : 'resourceController'
    })
    .when('/addnotes/:resourcename', {
        templateUrl : 'app/views/addnotes.html',
        controller  : 'resourceController'
    })
    .otherwise({
        redirectTo: '/'
    });

});
