var indexApp = angular.module('IndexApp',  ['ngRoute']);
//user
indexApp.controller('UserController', ['$scope', '$http',
    function ($scope, $http) {
        $http.get("/user/1?timestamp=1")
            .success(
                function (data, status, header, config) {
                    $scope.user = data['user'];
                }
            ).error(
                function (data) {
                    //alert("error");
                }
            );
    }
]);
//article
indexApp.controller('ArticleController', ['$scope', '$http',
    function ($scope, $http) {
        $http.get("/article?timestamp=1&userId=1")
            .success(
            function (data, status, header, config) {
                $scope.articles = data['bean'];
            }
        ).error(
            function (data) {
                //alert("error");
            }
        );
    }
]);

//article recommen
indexApp.controller('ArticleRecommenController',['$scope','$http',
    function($scope,$http){
        $http.get("/article/recomment?timestamp=1")
            .success(
                function(data){
                    $scope.articleRecommens = data['bean'];
                }
            ).error();
    }])