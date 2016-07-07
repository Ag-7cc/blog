var indexApp = angular.module('IndexApp',  ['ngRoute','ngAnimate']);
//index
indexApp.controller('IndexController',['$scope',
    function($scope) {
        $scope.pageClass = 'page-index';
    }
]);

//user
indexApp.controller('UserController', ['$scope', '$http',
    function ($scope, $http) {
        $http.get("/user/1?timestamp=1")
            .success(
                function (data, status, header, config) {
                    $scope.user = data['user'];
                }
            )
            .error(
                function (data) {
                    //alert("error");
                }
            );
    }
]);
//article
indexApp.controller('ArticleController', ['$scope', '$http',
    function ($scope, $http) {
        $scope.pageClass = 'page-article';
        $http.get("/article?timestamp=1&userId=1")
            .success(
                function (data, status, header, config) {
                    $scope.articles = data['bean'];
                }
            )
            .error(
                function (data) {
                    //alert("error");
                }
            );
    }
]);
//article detail
indexApp.controller('ArticleDetailController', ['$scope', '$http','$routeParams',
    function ($scope, $http,$routeParams) {
        $scope.pageClass = 'page-article-detail';
        $http.get("/article/"+$routeParams.id+"?timestamp=1")
            .success(
                function (data, status, header, config) {
                    $scope.article = data['bean'];
                }
            )
            .error(
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
            )
            .error();
    }
])