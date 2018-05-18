(function () {
    /*pattern 1*/
    angular.module('hitModule').factory('searchPatternOneFactory',
            ['$resource', function ($resource) {
                    return function (zip) {
                        var restUrl = "ws/patternQueries/langInZip";
                        return $resource(restUrl, {}, {
                            get: {
                                method: 'GET',
                                params: {'zip': zip},
                                isArray: false
                            }

                        });
                    }
                }]);


    /*pattern 2*/
    angular.module('hitModule').factory('searchPatternTwoFactory',
            ['$resource', function ($resource) {
                    return function (zip) {
                        var restUrl = "ws/patternQueries/fwInZip";
                        return $resource(restUrl, {}, {
                            get: {
                                method: 'GET',
                                params: {'zip': zip},
                                isArray: false
                            }

                        });
                    }
                }]);

    /*pattern 3*/

    angular.module('hitModule').factory('searchPatternThreeFactory',
            ['$resource', function ($resource) {
                    return function (state, tech) {
                        var restUrl = "ws/patternQueries/cityForTechInState";
                        return $resource(restUrl, {}, {
                            get: {
                                method: 'GET',
                                params: {'state': state,
                                    'tech': tech},
                                isArray: false
                            }

                        });
                    }
                }]);


    /*pattern 4*/
    angular.module('hitModule').factory('searchPatternFourFactory',
            ['$resource', function ($resource) {
                    return function (tech) {
                        var restUrl = "ws/patternQueries/stateForTech";
                        return $resource(restUrl, {}, {
                            get: {
                                method: 'GET',
                                params: {'tech': tech,
                                    'tech': tech},
                                isArray: false
                            }

                        });
                    }
                }]);
    
    

    angular.module('hitModule').controller('patternQueriesController', function ($scope, $rootScope,$location, $http, techListFactory, stateListFactory, searchPatternOneFactory, searchPatternTwoFactory, searchPatternThreeFactory,searchPatternFourFactory) {
        /*list of techs*/
        $scope.techs = techListFactory.get(
                function (data) {
                    console.log("success techs!");
                    //$scope.techs = data;
                }, function (error) {
            console.log("Error while getting REST data");
        });


        /*list of states*/
        $scope.states = stateListFactory.get(
                function (data) {
                    console.log("success states!");
                    //$scope.techs = data;
                }, function (error) {
            console.log("Error while getting REST data");
        });


        /*result pattern 1*/
        $scope.resultPatternOne = "";
        $scope.searchPatternOne = function () {
            $scope.resultPatternOne = searchPatternOneFactory($scope.zipCode).get();
        }

        /*result pattern 2*/
        $scope.resultPatternTwo = "";
        $scope.searchPatternTwo = function () {
            $scope.resultPatternTwo = searchPatternTwoFactory($scope.zipCode2).get();
        }

        /*result pattern 3*/
        $scope.resultPatternThree = "";
        $scope.searchPatternThree = function () {
            $scope.resultPatternThree = searchPatternThreeFactory($scope.state3, $scope.tech3).get();
        }


        /*result pattern 4*/
        $scope.resultPatternFour = "";
        $scope.searchPatternFour= function () {
            $scope.resultPatternFour = searchPatternFourFactory($scope.tech4).get();
        }
        


    });
})();