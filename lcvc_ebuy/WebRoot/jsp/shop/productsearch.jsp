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
<title>产品搜索</title>
<link href="<%=basePath%>jsp/shop/resources/css/shoplists.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="wrap">
		<iframe src="<%=basePath%>jsp/shop/header.jsp" style="height: 168px;"></iframe>
		<div class="content">
			<div class="block">
				<div class="crumb">
					<ul>
						<li><a href="<%=basePath%>shop/index">首页</a></li>
						<li><span>/</span></li>
						<li><a id="lists" href="#">搜索结果</a></li>
					</ul>
				</div>
				<div class="quick">
					<button class="all cur">全部</button>
					<button class="buy">已定</button>
					<button class="price">价格</button>
					<button class="sales">销量</button>
				</div>
				<div class="block-wrap">
                    <c:forEach var="productOfEach" items="${requestScope.list}">
					<div class="item">
						<a href="<%=basePath%>shop/product?productId=${productOfEach.id}"><img alt="" src="${productOfEach.picUrl}"></a>
						<label><em>${productOfEach.price}</em><span>${productOfEach.originalPrice}</span>订单数：${productOfEach.totalNumberOfOrder}</label>
						<h3>${productOfEach.name}<button class="buy">购买</button></h3>
					</div>
					</c:forEach>
				</div>
			</div>
			<br>
		<iframe src="<%=basePath%>jsp/shop/footer.html" style="height: 120px;"></iframe>
	</div>
</body>
</html>