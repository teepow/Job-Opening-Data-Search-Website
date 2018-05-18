(function () {
    angular.module('hitModule').controller('loginController', function ($scope,$rootScope, $location, $http) {
    console.log($rootScope.currentUser.isConnected);
    	if(!$rootScope.currentUser.isConnected) {
    	
    	    		$scope.userName = "";
    	    		$scope.password = "";

    	    		$scope.checkUser = function () {
            user = {
                userName: $scope.userName,
                password: $scope.password,
            }
            $http({
                method: 'POST',
                data: user, // object
                url: 'ws/user/checklogin'
            }).then(function (response) {

                console.log(response.data);
                if (response.data != 0) {
                    $rootScope.currentUser.id = response.data;
                    $rootScope.currentUser.username = $scope.userName; 
                    $rootScope.currentUser.isConnected = true;
                 //  console.log("data: " + response.data);
                    $location.path("/employer");
                } else {
                    alert("Incorrect User/Password");
                    $scope.userName = "";
                    $scope.password = "";
                }
            });
            
            
        };
    } else {
      	$location.path("/employer");
    }
    
    });
})();