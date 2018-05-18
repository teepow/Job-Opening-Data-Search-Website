//configure all routers
(function () {
    angular.module('hitModule').config(function ($routeProvider) {

        $routeProvider
                .when('/intro', {
                    templateUrl: './pages/intro.html',
                    controller: 'introController'

                })
                .when('/employer', {
                    templateUrl: './pages/employer.html',
                    controller: 'employerController'

                })
                .when('/login', {
                    templateUrl: './pages/login.html',
                    controller: 'loginController'

                })
                .when('/createUser', {
                    templateUrl: './pages/createuser.html',
                    controller: 'userController'

                })
                .when('/search', {
                    templateUrl: './pages/search.html',
                    controller: 'searchController'

                })

                .when('/', {
                    templateUrl: './pages/intro.html',
                    controller: 'introController'

                })
                .when('/userdeleted', {
                    templateUrl: './pages/userdeleted.html',
                    controller: 'userDeletedController'

                })
                .when('/patternqueries', {
                    templateUrl: './pages/patternqueries.html',
                    controller: 'patternQueriesController'

                }).when('/logout', {
                    templateUrl: './pages/logout.html',
                    controller: 'logoutController'

                })

        //.otherwise({
        //	redirectTo : '/',
        //	templateUrl : './index.html'
        //});

    });
})();