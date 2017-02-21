angular.module('usersApp').service('GroupService', function ($http) {
    this.findAll = function () {
        var url = 'api/group/all';
        return $http({
            method: "GET",
            url: url
        }).then(function successCallback(response) {
            return response;
        }, function errorCallback(response) {
            return response.status;
        });
    }

    this.findOneGroup = function (id) {
        var url = 'api/group/id/' + id;
        return $http({
            method: "GET",
            url: url
        }).then(function successCallback(response) {
            return response;
        }, function errorCallback(response) {
            return response.status;
        });
    }

    this.add = function (user) {
        return $http({
            method: "POST",
            url: '/api/group/add',
            data: user
        }).then(function successCallback(response) {
            return response;
        }, function errorCallback(response) {
            return response.status;
        });
    }

    this.edit = function (groupAndUserIds) {
        return $http({
            method: "POST",
            url: '/api/group/edit',
            data: groupAndUserIds
        }).then(function successCallback(response) {
            return response;
        }, function errorCallback(response) {
            return response.status;
        });
    }

    this.deleteGroup = function (id) {
        var url = 'api/group/delete/id/' + id;
        return $http({
            method: "POST",
            url: url
        }).then(function successCallback(response) {
            return response;
        }, function errorCallback(response) {
            return response.status;
        });
    }
});