var usersApp = angular.module('usersApp', ['ngRoute', 'ngResource', 'ui.bootstrap', 'angularjs-dropdown-multiselect']);


usersApp.config(function ($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'views/user/user.html',
            controller: 'UserController'
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