<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>橙汁园餐厅 -- 页头</title>

<link href="<%=basePath%>jsp/shop/resources/css/header.css" rel="stylesheet" type="text/css">

</head>
<body>
	<div class="header" style="height:168px;">
		<div class="toolbar">
			<a href="<%=basePath%>shop/index" target="top">&nbsp;首页&nbsp;</a>
			  <c:choose>
    			   <c:when  test="${sessionScope.customer!=null}">
        	          ${sessionScope.customer.name}，你好（ <a href="<%=basePath%>shop/logout" target="top">注销</a>）         
      	    	   </c:when>
     	    	   <c:otherwise>
      	            <a href="<%=basePath%>jsp/shop/signin.jsp" target="top">&nbsp;登录/注册&nbsp;</a>
     	    	    </c:otherwise>
    	      </c:choose>
			<a href="<%=basePath%>jsp/shop/cart/shopcart.jsp" target="top">&nbsp;购物车&nbsp;</a>
		</div>
		<div class="content">
			<img alt="" src="<%=basePath%>jsp/shop/resources/images/logo.png">
			<form class="search-wrap" target="_top" action="<%=basePath%>shop/doSearchProduct">
				<input type="text" name="name" value="${requestScope.name}"/>
				<button id="submit">搜索</button>
			</form>
		</div>
		<div class="menu">
			<ul>
				<li class="cur"><a href="<%=basePath%>shop/index" target="top">全部分类</a></li>
				<li><a href="<%=basePath%>shop/index" style="color: #d2364c;" target="top">首页</a></li>
				<li><a href="#">今日新品</a></li>
				<li><a href="#">热门订单</a></li>
				<li><a href="#">限时秒杀</a></li>
				<li><a href="#">拼团</a></li>
				<li><a href="#">特惠</a></li>
			</ul>
		</div>
	</div>
</body>
</html>