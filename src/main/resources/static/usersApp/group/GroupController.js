angular.module('usersApp').controller('GroupController', function ($scope, GroupService) {
    $scope.name;
    $scope.groups;

    var loadAllGroups = function () {
        GroupService
            .findAll()
            .then(function (response) {
                if (response.status == 200) {
                    $scope.groups = response.data;
                    //alert(angular.toJson(response.data));
                }
            })
    }
    loadAllGroups();

    $scope.addGroup = function () {
        GroupService
            .add($scope.name)
            .then(function (response) {
                if (response.status == 200) {
                    loadAllGroups();
                    $scope.name="";
                    alert('Group has been added.');
                } else {
                    alert('We have problem. Perhaps the group already exists.');
                }
            });
    }

    $scope.deleteGroup = function (id) {
        GroupService
            .deleteGroup(id)
            .then(function (response) {
                if (response.status == 200) {
                    loadAllGroups();
                    alert("The group has been deleted.")
                } else {
                    alert("Group was not removed.")
                }
            })
    }
});