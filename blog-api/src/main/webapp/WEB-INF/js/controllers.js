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
        $http.get("/article/1?timestamp=1")
            .success(
            function (data, status, header, config) {
                $scope.items = data['bean'];
            }
        ).error(
            function (data) {
                //alert("error");
            }
        );
    }
]);