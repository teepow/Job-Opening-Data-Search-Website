(function () {

    angular.module('hitModule').controller('userController', function ($scope, $rootScope,$location, $http) {
        $scope.userName = "";
        $scope.password = "";

        $scope.addUser = function () {
            if ($scope.userName && $scope.password) {
                user = {
                    userName: $scope.userName,
                    password: $scope.password,
                }
                $http({
                    method: 'POST',
                    data: user, // object
                    url: 'ws/user/isuser'
                })
                        .then(
                                function (response) {
                                    if (response.data != 0) {
                                        // user already exists
                                        alert("Username already exists. Please try a different UserName");
                                        $scope.userName = "";
                                        $scope.password = "";
                                    } else {
                                        $http({
                                            method: 'POST',
                                            data: user, // object
                                            url: 'ws/user/adduser'
                                        })
                                                .then(
                                                        function (response) {
                                                            console.log("POST success");
                                                            console.log(response.data);
                                                            if (response.data != 0) {
                                                                alert("User was created succesfully!");
                                                                $scope.userName = "";
                                                                $scope.password = "";
                                                            	$rootScope.currentUser = {
                                                                        id: 0,
                                                                        username:"",
                                                                        isConnected:false
                                                                    }
                                                                $location.path("/intro");
                                                            } else {
                                                                alert("Issue when adding user");
                                                            }

                                                        });
                                    }

                                });

            } else {
                alert("Missing login/password");
            }
        };
    });
})();