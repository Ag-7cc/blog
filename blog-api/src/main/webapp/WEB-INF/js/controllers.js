var indexApp = angular.module('IndexApp', ['ngRoute', 'ngAnimate']);
//index
indexApp.controller('IndexController', ['$scope',
    function ($scope) {
        $scope.pageClass = 'page-index';
    }
]);

//user
indexApp.controller('UserController', ['$scope', '$http',
    function ($scope, $http) {
        $http.get(Request.getUrl("/user/1"))
            .success(
                function (data, status, header, config) {
                    $scope.user = data['user'];
                }
            )
            .error();
    }
]);

//article
indexApp.controller('ArticleController', ['$scope', '$http', '$sce',
    function ($scope, $http, $sce) {
        $scope.pageClass = 'page-article';
        $http.get(Request.getUrl("/article", "userId=1"))
            .success(
                function (data, status, header, config) {
                    var articles = data['bean'];
                    for(var idx in articles){
                        articles[idx].articleContentHtml =  $sce.trustAsHtml(articles[idx].content);
                        //alert($(articles[idx].articleContentHtml).);
                        // articles[idx].articleContentHtml.cloneNode(true);
                        // alert(articles[idx].articleContentHtml.nodeType)
                        // alert(articles[idx].contextText = $(articles[idx].articleContentHtml).text());

                        // if($(articles[idx].articleContentHtml)[0].hasChildNodes()){
                        //     articles[idx].contextText = $(articles[idx].articleContentHtml).text();
                        // }else{
                        //     articles[idx].contextText = articles[idx].articleContentHtml;
                        // }

                        // alert(documentIsHTML(articles[idx].articleContentHtml)+"   "+articles[idx].articleContentHtml);

                        //articles[idx].contextText = $(articles[idx].articleContentHtml).text();
                        //alert(articles[idx].contextText);
                        // articles[idx].contextText = $(articles[idx].articleContentHtml).text();
                    }
                    $scope.articles = articles;
                }
            )
            .error();
    }
]);
//article detail
indexApp.controller('ArticleDetailController', ['$scope', '$http', '$routeParams', '$sce',
    function ($scope, $http, $routeParams, $sce) {
        var ue = UE.getEditor('container');
        $scope.pageClass = 'page-article-detail';
        $http.get(Request.getUrl("/article/" + $routeParams.id))
            .success(
                function (data, status, header, config) {
                    $scope.article = data['bean'];
                    $scope.articleContentHtml = $sce.trustAsHtml($scope.article.content);
                }
            )
            .error();
    }
]);

//article recommen
indexApp.controller('ArticleRecommenController', ['$scope', '$http',
    function ($scope, $http) {
        $http.get(Request.getUrl("/article/recomment"))
            .success(
                function (data) {
                    $scope.articleRecommens = data['bean'];
                }
            )
            .error();
    }
])