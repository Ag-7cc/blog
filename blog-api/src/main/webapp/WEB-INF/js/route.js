indexApp.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'pages/template/main.html'
        })
        .when('/article', {
            templateUrl: 'pages/template/article_list.html'
        })
        .when('/article/{id}',{
            templateUrl: 'pages/template/article_list.html'
        })
        .otherwise({redirectTo: '/'});
}]);
