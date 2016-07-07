indexApp.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'pages/template/main.html',
            controller: 'IndexController'
        })
        .when('/article', {
            templateUrl: 'pages/template/article_list.html',
            controller: 'ArticleController'
        })
        .when('/article/:id',{
            templateUrl: 'pages/template/article_detail.html',
            controller: 'ArticleDetailController'
        })
        .when('/album',{
            templateUrl: 'pages/template/album_list.html'
        })
        .otherwise({redirectTo: '/'});
}]);
