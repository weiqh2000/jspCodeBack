<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<title>橙汁园餐厅 -- 首页</title>
<link href="<%=basePath%>jsp/shop/resources/css/index.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="wrap">
	    <%-- <jsp:include page="header.jsp" flush="true"/> --%>
		<iframe src="<%=basePath%>jsp/shop/header.jsp" style="height: 168px;"></iframe>
		<div class="content">
			<div class="logo">
				<img alt="" src="<%=basePath%>jsp/shop/resources/images/index-logo.jpg">
				<div class="menu">
					<ul>
					    <c:forEach var="productTypeOfEach" items="${requestScope.productTypelist}">
						<li><a href="<%=basePath%>shop/producttype?productTypeId=${productTypeOfEach.id}"><img class="left" style="width:41px;height:41px;" alt="" src="${productTypeOfEach.imageUrl}">${productTypeOfEach.name}<img class="right" alt="" src="<%=basePath%>jsp/shop/resources/images/menu-right.png"></a></li>
						</c:forEach>					
					</ul>
				</div>
			</div>
			<div class="block">
				<div class="title">
					<label class="new">新品</label>
					<a href="#">更多&gt;&gt;</a>
				</div>
				<div class="block-wrap">
				    <c:forEach var="newProductOfEach" items="${requestScope.newProducts}">
					<div class="item">
						<a href="<%=basePath%>shop/showProduct?id=${newProductOfEach.id}"><img alt="" src="${newProductOfEach.picUrl}"></a>
						<label><em>${newProductOfEach.price}</em><span>${newProductOfEach.originalPrice}</span>库存：${newProductOfEach.number}</label>
						<h3>${newProductOfEach.name}</h3>
					</div>
					</c:forEach>
				</div>
			</div>
			<div class="block">
				<div class="title">
					<label class="hot">热门订单</label>
					<a href="#">更多&gt;&gt;</a>
				</div>
				<div class="block-wrap">
					<c:forEach var="hotProductOfEach" items="${requestScope.hotProducts}">
					<div class="item">
						<a href="<%=basePath%>shop/showProduct?id=${hotProductOfEach.id}"><img alt="" src="${hotProductOfEach.picUrl}"></a>
						<label><em>${hotProductOfEach.price}</em><span>${hotProductOfEach.originalPrice}</span>关注：${hotProductOfEach.click}</label>
						<h3>${hotProductOfEach.name}</h3>
					</div>
					</c:forEach>
				</div>
			</div>
		</div>
		<iframe src="<%=basePath%>jsp/shop/footer.html" style="height: 120px;"></iframe>
	</div>
</body>
</html>