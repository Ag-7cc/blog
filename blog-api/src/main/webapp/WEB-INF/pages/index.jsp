<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link href="css/personalblog.css" rel="stylesheet">
<link href="css/animation.css" rel="stylesheet">

<!-- 返回顶部调用 begin -->
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/js.js"></script>
<!-- 返回顶部调用 end-->
<!--[if lt IE 9]>
<script src="js/modernizr.js"></script>
<![endif]-->
<script src="js/framework/1.3.0.14/angular.min.js"></script>
<script src="js/framework/1.3.0.14/angular-route.js"></script>

<%--自定义--%>
<script src="js/controllers.js"></script>
<script src="js/route.js"></script>
<script src="js/common/util.js"></script>

<!doctype html>
<html ng-app="IndexApp">
<head>
    <meta charset="UTF-8">
    <title>Ag丶七梦安生个人博客</title>
    <meta name="keywords" content="博客,单其贝,shanqibei,vic"/>
    <meta name="description" content="单其贝的个人博客"/>
</head>
<body>
<jsp:include page="commons/header.jsp"/>
<!--header end-->
<div ng-view></div>
<jsp:include page="commons/footer.jsp"/>
</body>
</html>