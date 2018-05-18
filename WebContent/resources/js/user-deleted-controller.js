(function () {

    angular.module('hitModule').controller('userDeletedController', function ($scope, $rootScope, $location, $http) {
        
    	$rootScope.currentUser = {
                id: 0,
                username:"",
                isConnected:false
            }
        
    });
})();