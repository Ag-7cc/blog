<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header-taglib.jsp" %>

<link rel="shortcut icon" href="images/icon/favicon.ico"/>
<link rel="bookmark" href="images/icon/favicon.ico"/>

<link href="css/main.css" rel="stylesheet">
<link href="css/animation.css" rel="stylesheet">

<header>
    <nav id="nav">
        <ul>
            <li><a href="#/" class="nav_current">网站首页</a></li>
            <li><a href="#/article" target="_self" title="我的博文">我的博文</a></li>
            <li><a href="#1" target="_self" title="相册">相册</a></li>
            <li><a href="#2" target="_self" title="留言板">留言板</a></li>
            <li><a href="#3" target="_self" title="HTML5 / CSS3">HTML5 / CSS3</a></li>
            <li><a href="#4" target="_self" title="技术探讨">技术探讨</a></li>
            <li><a href="#5" target="_self" title="慢生活">慢生活</a></li>
            <li><a href="#6" target="_self" title="碎言碎语">碎言碎语</a></li>
            <li><a href="#7" target="_self" title="JS 实例代码演示">JS实例</a></li>
        </ul>
    </nav>
</header>
<script>
    $(function(){
        $('#nav a').click(function(){
            $('#nav a.nav_current').removeClass('nav_current');
            $(this).addClass('nav_current');
        });
    });
</script>
