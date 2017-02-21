angular.module('usersApp').controller('UserEditController', function ($scope, UserService, $routeParams, $filter) {
    $scope.nameOfUser;
    $scope.password;
    $scope.firstName;
    $scope.lastName;
    $scope.dateOfBirth;

    $scope.dt = new Date();
    var loadOneUser = function (id) {
        UserService
            .findOneUser(id)
            .then(function (response) {
                if (response.status == 200) {
                    $scope.nameOfUser = response.data.name;
                    //$scope.password = response.data.password;
                    $scope.firstName = response.data.firstName;
                    $scope.lastName = response.data.lastName;
                    $scope.dt = compareDate(response.data.dateOfBirth);
                }
            })
    }
    loadOneUser($routeParams.id);

    $scope.options = {
        datepickerMode: "day"
    };

    function compareDate(str1) {
        var dt1 = parseInt(str1.substring(0, 2));
        var mon1 = parseInt(str1.substring(3, 5));
        var yr1 = parseInt(str1.substring(6, 10));
        var date1 = new Date(yr1, mon1 - 1, dt1);
        return date1;
    }

    $scope.saveChanges = function () {
        var date = new Date();
        date = $filter('date')($scope.dt, "dd/MM/yyyy");
        var user = {
            "id": $routeParams.id,
            "name": $scope.nameOfUser,
            //"password": $scope.password,
            "firstName": $scope.firstName,
            "lastName": $scope.lastName,
            "dateOfBirth": date
        }
        UserService
            .editUser(user)
            .then(function (response) {
                if (response.status == 200) {
                    alert('User has been modified.');
                } else {
                    alert('The user has not been modified.');
                }
            });
    }
});