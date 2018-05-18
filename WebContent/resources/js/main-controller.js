(function () {
    angular.module('hitModule').factory('techListFactory',
            ['$resource', function ($resource) {
                    var restUrl = "ws/tech/list";
                    return $resource(restUrl, {}, {
                        get: {
                            method: 'GET',
                            params: {},
                            isArray: true
                        }

                    });

                }]);

    angular.module('hitModule').factory('stateListFactory',
            ['$resource', function ($resource) {
                    var restUrl = "ws/location/statelist";
                    return $resource(restUrl, {}, {
                        get: {
                            method: 'GET',
                            params: {},
                            isArray: true
                        }

                    });

                }]);
            
     angular.module('hitModule').factory('cityListFactory',
            ['$resource', function ($resource) {
                return function(state) {
                    var restUrl = "ws/location/citylist";
                    return $resource(restUrl, {}, {
                        get: {
                            method: 'GET',
                            params: {'state': state },
                            isArray: true
                        }

                    });
                }
                }]);
            
      
    
    
    
    angular.module('hitModule').controller('mainController', function ($rootScope) {
        $rootScope.currentUser = {
            id: 0,
            username:"",
            isConnected:false
        }
    });
})();