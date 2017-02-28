angular.module('usersApp').controller('UserController', function ($scope, UserService, GroupService, $filter) {
    $scope.message = "";

    $scope.groups = [];
    $scope.selectedGroupsModel = [];
    $scope.groupsSettings = {
        scrollableHeight: '100px',
        scrollable: true,
        displayProp: 'name', idProp: 'id'
        //externalIdProp: ''
    };

    $scope.nameOfUser;
    $scope.password;
    $scope.firstName;
    $scope.lastName;
    $scope.dateOfBirth;
    $scope.dtOfBirth = new Date();
    $scope.confirmPassword="";

    var loadAllGroups = function () {
        GroupService
            .findAll()
            .then(function (response) {
                if (response.status == 200) {

                    for (var i = 0; i < response.data.length; i++) {
                        $scope.groups.push(response.data[i]);
                    }
                }
            })
    }
    loadAllGroups();

    $scope.addUser = function () {
        if(angular.equals($scope.password, $scope.confirmPassword)){
            var idOfSelectedGroups = [];
            for (var i = 0; i < $scope.selectedGroupsModel.length; i++) {
                idOfSelectedGroups.push($scope.selectedGroupsModel[i].id);
            }

            var date = new Date();
            date = $filter('date')($scope.dtOfBirth, "dd/MM/yyyy");
            var user = {
                "name": $scope.nameOfUser,
                "password": $scope.password,
                "firstName": $scope.firstName,
                "lastName": $scope.lastName,
                "dateOfBirth": date
            }
            var userAndGroup = {
                "user": user,
                "idOfGroups": idOfSelectedGroups,

            }
            UserService
                .add(userAndGroup)
                .then(function (response) {
                    if (response.status == 200) {
                        loadAllUsers();
                        alert('User has been added.');
                        clearFields();
                    } else {
                        alert('We have problem. Chekc all fields.' );
                    }
                });
        }else {
            alert("Different passwords.");
        }
    }
    var loadAllUsers = function () {
        UserService
            .findAll()
            .then(function (response) {
                if (response.status == 200) {
                    $scope.users = response.data;
                }
            })
    }
    loadAllUsers();

    var clearFields = function () {
        $scope.nameOfUser = "";
        $scope.password = "";
        $scope.firstName = "";
        $scope.lastName = "";
        $scope.dateOfBirth = "";
        $scope.confirmPassword="";
    }

    $scope.deleteUser = function (id) {
        UserService
            .deleteUser(id)
            .then(function (response) {
                if (response.status == 200) {
                    alert("The user has been deleted.")
                } else {
                    alert("User was not removed.")
                }
            })
    }
    $scope.test = function(){
        //Proszę kod, ja sobie wpisalem na sztywno, ale Ty będziesz bral ze slidera
        var R=15;
        var L=0.075;
        var Uz=25;
        var Tau=L/R;
        var dt=0.002;
        var n=35;
        var pr = []

        for(var i=0; i<n; i++){
            pr[i] = 0;
        }

        for(var i=0; i<n; i++){
            pr[i+1] = pr[i+1] = pr[i]+dt*(-pr[i]/Tau+Uz/L);
        }
        $scope.wyniki = []
        for(var i=0; i<n; i++){
            var zm = {
                id:"liczba",
                wartosc: pr[i].toFixed(4)
            }
            console.log(pr[i].toFixed(4));
            $scope.wyniki.push(zm);
        }


    }
});