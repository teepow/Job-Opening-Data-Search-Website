(function () {
    angular.module('hitModule').controller('logoutController', function ($rootScope) {
        $rootScope.currentUser = {
            id: 0,
            username:"",
            isConnected:false
        }
    });
})();