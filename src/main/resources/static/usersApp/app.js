var usersApp = angular.module('usersApp', ['ngRoute', 'ngResource', 'ui.bootstrap', 'http-auth-interceptor', 'angularjs-dropdown-multiselect']);


usersApp.config(function ($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'views/user/user.html',
            controller: 'UserController'
        })
        .when('/login', {
            templateUrl: 'views/user/login.html',
            controller: 'LoginController'
        })
        .when('/groups', {
            templateUrl: 'views/group/group.html',
            controller: 'GroupController'
        })
        .when('/groups/edit/:id', {
            templateUrl: 'views/group/edit.html',
            controller: 'EditController'
        })
        .when('/users/edit/:id', {
            templateUrl: 'views/user/edit.html',
            controller: 'UserEditController'
        })
        .otherwise({redirectTo: '/'});
});

usersApp.config(['$httpProvider', function($httpProvider) {
    //initialize get if not there
    if (!$httpProvider.defaults.headers.get) {
        $httpProvider.defaults.headers.get = {};
    }

    // Answer edited to include suggestions from comments
    // because previous version of code introduced browser-related errors

    //disable IE ajax request caching
    $httpProvider.defaults.headers.get['If-Modified-Since'] = 'Mon, 26 Jul 1997 05:00:00 GMT';
    // extra
    $httpProvider.defaults.headers.get['Cache-Control'] = 'no-cache';
    $httpProvider.defaults.headers.get['Pragma'] = 'no-cache';
}]);

usersApp.constant('USER_ROLES', {
    all: '*',
    admin: 'admin',
    user: 'user'
});

// usersApp.run(function ($rootScope, $location, $http, AuthSharedService, Session, USER_ROLES, $q, $timeout) {
//
//     $rootScope.$on('$routeChangeStart', function (event, next) {
//
//         if(next.originalPath === "/login" && $rootScope.authenticated) {
//             event.preventDefault();
//         } else if (next.access && next.access.loginRequired && !$rootScope.authenticated) {
//             event.preventDefault();
//             $rootScope.$broadcast("event:auth-loginRequired", {});
//         } else if (next.access && !AuthSharedService.isAuthorized(next.access.authorizedRoles)) {
//             event.preventDefault();
//             $rootScope.$broadcast("event:auth-forbidden", {});
//         }
//     });
//
//     $rootScope.$on('$routeChangeSuccess', function (scope, next, current) {
//         $rootScope.$evalAsync(function () {
//             //$.material.init();
//         });
//     });
//
//     // Call when the the client is confirmed
//     $rootScope.$on('event:auth-loginConfirmed', function (event, data) {
//         console.log('login confirmed start ' + data);
//         $rootScope.loadingAccount = false;
//         var nextLocation = ($rootScope.requestedUrl ? $rootScope.requestedUrl : "/home");
//         var delay = ($location.path() === "/loading" ? 1500 : 0);
//
//         $timeout(function () {
//             Session.create(data);
//             $rootScope.account = Session;
//             $rootScope.authenticated = true;
//             $location.path(nextLocation).replace();
//         }, delay);
//
//     });
//
//     // Call when the 401 response is returned by the server
//     $rootScope.$on('event:auth-loginRequired', function (event, data) {
//         if ($rootScope.loadingAccount && data.status !== 401) {
//             $rootScope.requestedUrl = $location.path()
//             $location.path('/loading');
//         } else {
//             Session.invalidate();
//             $rootScope.authenticated = false;
//             $rootScope.loadingAccount = false;
//             $location.path('/login');
//         }
//     });
//
//     // Call when the 403 response is returned by the server
//     $rootScope.$on('event:auth-forbidden', function (rejection) {
//         $rootScope.$evalAsync(function () {
//             $location.path('/error/403').replace();
//         });
//     });
//
//     // Call when the user logs out
//     $rootScope.$on('event:auth-loginCancelled', function () {
//         $location.path('/login').replace();
//     });
//
//     // Get already authenticated user account
//     AuthSharedService.getAccount();
//
//
// });