(function () {
    
    angular.module('hitModule').factory('searchOneFactory',
        ['$resource', function ($resource) {
        return function (techOne, techTwo, city, state) {
        var restUrl = "ws/complexQueries/twoTechsByCityState";
                return $resource(restUrl, {}, {
                get: {
                method: 'GET',
                        params: {'techOne': techOne,
                                'techTwo' : techTwo,
                                'city' : city,
                                'state': state},
                         isArray: false
                }

                });
                }
        }]);   
    
     angular.module('hitModule').factory('searchTwoFactory',
        ['$resource', function ($resource) {
        return function (techOne, techTwo, zip) {
        var restUrl = "ws/complexQueries/twoTechsByZip";
                return $resource(restUrl, {}, {
                get: {
                method: 'GET',
                        params: {'techOne': techOne,
                                'techTwo' : techTwo,
                                'zip' : zip},
                             
                         isArray: false
                }

                });
                }
        }]);    
            

        angular.module('hitModule').factory('searchThreeFactory',
        ['$resource', function ($resource) {
        return function (tech,cityOne,stateOne,cityTwo,stateTwo) {
        var restUrl = "ws/complexQueries/twoCityStatesForTech";
                return $resource(restUrl, {}, {
                get: {
                method: 'GET',
                        params: {'tech': tech,
                                'cityOne' : cityOne,
                                'stateOne' : stateOne,
                                'cityTwo': cityTwo,
                                'stateTwo': stateTwo},
                             
                         isArray: false
                }

                });
                }
        }]);  
    
    
     angular.module('hitModule').factory('searchFourthFactory',
        ['$resource', function ($resource) {
        return function (tech,city,state) {
        var restUrl = "ws/complexQueries/fwForLangInCityState";
                return $resource(restUrl, {}, {
                get: {
                method: 'GET',
                        params: {'language': tech,
                                'city' : city,
                                'state' : state
                                },
                             
                         isArray: false
                }

                });
                }
        }]);  
    
    angular.module('hitModule').factory('searchFifthFactory',
        ['$resource', function ($resource) {
        return function (state,tech,numJobs) {
        var restUrl = "ws/complexQueries/cityStateNJobsForTech";
                return $resource(restUrl, {}, {
                get: {
                method: 'GET',
                        params: {'state': state,
                                'tech' : tech,
                                'numJobs' : numJobs
                                },
                             
                         isArray: false
                }

                });
                }
        }]);
    angular.module('hitModule').controller('searchController', function ($scope, $rootScope,$location, $http, techListFactory, stateListFactory,cityListFactory,searchOneFactory,searchTwoFactory, searchThreeFactory,searchFourthFactory,searchFifthFactory) {


      //  $scope.tech1="";
       // $scope.tech2="";
        //list of techs
        $scope.techs = techListFactory.get(
                function (data) {
                    console.log("success techs!");
                    //$scope.techs = data;
                }, function (error) {
            console.log("Error while getting REST data");
        });
        console.log($scope.techs);
        

        //list of states
        $scope.states = stateListFactory.get(
                function (data) {
                    console.log("success states!");
                    //$scope.techs = data;
                }, function (error) {
            console.log("Error while getting REST data");
        });
       console.log($scope.states);
       
       

       $scope.getCities = function() {
           console.log("click");
           $scope.cities = cityListFactory($scope.state).get();
           console.log($scope.cities);
       }
       
       
       $scope.resultOne = "";
        
        $scope.searchOne = function() {
             $scope.resultOne = searchOneFactory($scope.techOne, $scope.techTwo, $scope.city, $scope.state).get();
        }
        
        $scope.resultTwo = "";
        $scope.searchTwo = function() {
             $scope.resultTwo = searchTwoFactory($scope.techOneZip, $scope.techTwoZip, $scope.zipCode).get();
        }
        
        
        $scope.getCitiesOne = function() {
           $scope.citiesOne = cityListFactory($scope.stateOne).get();
        }
        $scope.getCitiesTwo = function() {
           $scope.citiesTwo = cityListFactory($scope.stateTwo).get();
        }
       
       
        $scope.resultThree = "";
        $scope.searchThree= function() {
             $scope.resultThree = searchThreeFactory($scope.techThird, $scope.cityOne, $scope.stateOne, $scope.cityTwo, $scope.stateTwo).get();
        }
        
        
        $scope.getCitiesFourth = function() {
            $scope.citiesFourth = cityListFactory($scope.stateFourth).get();
        }
        
        $scope.resultFourth = "";
        $scope.searchFourth= function() {
             $scope.resultFourth = searchFourthFactory($scope.techFouth, $scope.cityFourth, $scope.stateFourth).get();
        }
        
        
        
       $scope.resultFifth = "";
       $scope.searchFifth = function() {
           $scope.resultFifth = searchFifthFactory($scope.stateFifth, $scope.techFifth, $scope.numJobs).get();
        }
        
 
    });
})();