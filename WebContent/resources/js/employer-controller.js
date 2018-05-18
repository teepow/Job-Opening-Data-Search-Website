(function () {

    angular.module('hitModule').factory('jobListFactory',
            ['$resource', function ($resource) {
                    return function (userId) {
                        var restUrl = "ws/otherRequirements/UJOList";
                        return $resource(restUrl, {}, {
                            get: {
                                method: 'GET',
                                params: {'userId': userId
                                },
                                isArray: true
                            }

                        });
                    }
                }]);




    //techListFactory
    //stateListFactory
    //cityListFactory






    angular.module('hitModule').controller('employerController', function ($scope, $rootScope, $location, $http, jobListFactory, techListFactory, stateListFactory, cityListFactory) {
        var user = {
            id: $rootScope.currentUser.id
        }

        $scope.techs = techListFactory.get(
                function (data) {
                }, function (error) {
            console.log("Error while getting REST data");
        });
        console.log($scope.techs);


        //list of states
        /*  $scope.states = stateListFactory.get(
         function (data) {
         console.log("success states!");
         //$scope.techs = data;
         }, function (error) {
         console.log("Error while getting REST data");
         });
         console.log($scope.states);*/

        //cities
        /*  $scope.getCities = function () {
         $scope.cities = cityListFactory($scope.state).get();
         }*/


        $scope.jobList = jobListFactory($rootScope.currentUser.id).get(
                function (data) {
                    $scope.jobListSize = data.length;
                }, function (error) {
            console.log("Error while getting REST data");
        });

        /*add user*/
        $scope.addJob = function () {

            if ($scope.tech && $scope.zipCode) {
                // console.log("tech: " + $scope.tech);
                //console.log($scope.tech);
                // var partsOfStr = ($scope.tech).split(',');
                // console.log(partsOfStr);

                var newJob = {
                    //  city: $scope.city,
                    //  state: $scope.state,
                    zipcode: $scope.zipCode,
                    userId: $rootScope.currentUser.id,
                    techs: JSON.parse(JSON.stringify($scope.tech))
                };



                if (confirm('Are you sure you want to create this job?')) {
                    $http({
                        method: 'POST',
                        data: newJob, // object
                        url: 'ws/otherRequirements/addJobOpening'
                    })
                            .then(
                                    function (response) {
                                        console.log("POST success");
                                        alert("A job was added to your list!");
                                        
                                        $scope.jobList = jobListFactory($rootScope.currentUser.id).get(
                                                function (data) {
                                                    $scope.jobListSize = data.length;
                                                }, function (error) {
                                            console.log("Error while getting REST data");
                                        });
                                        
                                         $scope.zipCode ="";
                                         $scope.tech="";
                                    });

                } else {
                    console.log("cancel");
                }
            } else {
                alert("Enter all info")
            }
        };

        /* delete user */
        $scope.deleteAccount = function () {
            if (confirm('Are you sure you want to delete your user?')) {
                console.log("delete");
                $http({
                    method: 'POST',
                    data: user, // object
                    url: 'ws/user/deleteUser'
                })
                        .then(
                                function (response) {
                                    console.log("POST success");
                                    $location.path("/userdeleted");

                                });

            } else {
                console.log("cancel");
            }
        };
    });
})();