<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link href="css/main.css" rel="stylesheet">
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
<script src="js/framework/1.3.0.14/angular-animate.js"></script>

<!-- ueditor 配置文件 -->
<script type="text/javascript" src="js/framework/ueditor/ueditor.config.js"></script>
<!-- ueditor 编辑器源码文件 -->
<script type="text/javascript" src="js/framework/ueditor/ueditor.all.js"></script>

<%--自定义--%>
<script src="js/controllers.js"></script>
<script src="js/route.js"></script>
<script src="js/directives.js"></script>
<script src="js/common/common.js"></script>

<!doctype html>
<html ng-app="IndexApp">
<head>
    <meta charset="UTF-8">
    <title>Vic丶离楛的个人博客</title>
    <meta name="keywords" content="博客,单其贝,shanqibei,vic,离楛"/>
    <meta name="description" content="单其贝的个人博客"/>
    <%--QQ--%>
    <meta property="qc:admins" content="35402540316112516375" />
    <%--百度--%>
    <meta name="baidu-site-verification" content="4GIrk08MDQ" />

    <link rel="shortcut icon" href="images/icon/favicon.ico"/>
    <link rel="bookmark" href="images/icon/favicon.ico"/>
    <!-- 百度站长-被动推送 -->
    <script>
        (function(){
            var bp = document.createElement('script');
            var curProtocol = window.location.protocol.split(':')[0];
            if (curProtocol === 'https') {
                bp.src = 'https://zz.bdstatic.com/linksubmit/push.js';
            }
            else {
                bp.src = 'http://push.zhanzhang.baidu.com/push.js';
            }
            var s = document.getElementsByTagName("script")[0];
            s.parentNode.insertBefore(bp, s);
        })();
    </script>
    <script>
        var _hmt = _hmt || [];
        (function() {
            var hm = document.createElement("script");
            hm.src = "https://hm.baidu.com/hm.js?05dff5ea87365a46529ff9bbee7cdf14";
            var s = document.getElementsByTagName("script")[0];
            s.parentNode.insertBefore(hm, s);
        })();
    </script>

</head>
<body>
<jsp:include page="commons/header.jsp"/>
<!--header end-->
<div ng-class="pageClass" ng-view></div>
<jsp:include page="commons/footer.jsp"/>
</body>
</html>