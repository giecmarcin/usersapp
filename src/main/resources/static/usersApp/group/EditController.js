angular.module('usersApp').controller('EditController', function ($scope, GroupService, $routeParams, UserService) {
    $scope.nameOfGroup;
    $scope.users;
    $scope.members = [];
    $scope.selectedMembersModel = [];
    $scope.membersSettings = {
        scrollableHeight: '100px',
        scrollable: true,
        displayProp: 'name', idProp: 'id',
        externalIdProp: ''
    };



    var loadOneGroup = function (id) {
        GroupService
            .findOneGroup(id)
            .then(function (response) {
                if (response.status == 200) {
                    $scope.nameOfGroup = response.data.name;
                    $scope.oldMembers = response.data.users;
                    loadAllUsers(response.data.users);

                }
            })
    }
    var loadAllUsers = function (membersOfGroup) {
        UserService
            .findAll()
            .then(function (response) {
                if (response.status == 200) {
                    $scope.users = response.data;
                    loadData($scope.users, membersOfGroup)
                }
            })
    }
    loadOneGroup($routeParams.id);

    var loadData = function (users, membersOfGroup) {
        for(var i=0; i<users.length; i++){
            $scope.members.push(users[i]);
        }
        for(var i=0; i<membersOfGroup.length; i++){
            $scope.selectedMembersModel.push(membersOfGroup[i]);
        }
    }
    
    $scope.saveChanges = function () {
        $scope.idOfSelectedUsers = [];

        var nameAndUsers = {
            "name": $scope.nameOfGroup,
            "users" : $scope.selectedMembersModel,
            "idOfGroup": $routeParams.id
        }

        GroupService
            .edit(nameAndUsers)
            .then(function (response) {
                if (response.status == 200) {
                    alert('Group has been modified');
                } else {
                    alert('Not modified.');
                }
            });
    }
});