<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>网站后台管理系统HTML模板--模板之家 www.cssmoban.com</title>
<link href="<%=basePath%>jsp/admin/css/style.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="<%=basePath%>jsp/admin/js/jquery.js"></script>

<script type="text/javascript">
//用于弹出窗口，将服务器返回的数据提交，本处用于账户提交后的状态

	function alert_myMessage() {
		var message = "${requestScope.myMessage}";
		if (message != "") {
			alert(message);
		}
	}

	$(document).ready(function() {
		//用于弹出窗口，将服务器返回的数据提交，本处用于账户提交后的状态
		alert_myMessage();
		
		$('.tablelist tbody tr:odd').addClass('odd');//表格隔行变色
	
		$("a[name='deleteProductType']").click(function() {
			 if(window.confirm('确定要删除吗？删除后无法恢复')){
			     var id=$(this).attr("alt");
	             window.location.href="<%=basePath%>admin/producttype/doDeleteProductType?id="+id; 
	         }
		});

	});
</script>


</head>


<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="<%=basePath%>admin/adminmanage/adminmanage">管理账户管理</a></li>
		</ul>
	</div>

	<div class="rightinfo">

		<div class="tools">

			<ul class="toolbar">
			    <a href="<%=basePath%>admin/producttype/toAddProductType">
				<li class="click">
				   <span>
				      <img src="<%=basePath%>jsp/admin/images/t01.png" />
				   </span>添加产品分类
				</li>
				</a>
			</ul>


			<!--  <ul class="toolbar1">
        <li><span><img src="jsp/admin/images/t05.png" /></span>设置</li>
        </ul> -->

		</div>


		<table class="tablelist">
			<thead>
				<tr>
					<th style="width:40px;"><input name="" type="checkbox" value="" />
					</th>
					<th style="width:20%;">产品分类名</th>
					<th style="width:10%;">图片</th>
					<th style="width:25%;">外部链接</th>
					<th style="width:10%;">优先级</th>
					<th style="width:10%;">产品数量</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>


				<c:forEach var="productTypeOfEach" items="${requestScope.list}">
					<tr>
						<td><input name="" type="checkbox"
							value="${productTypeOfEach.id}" /></td>
						<td>${productTypeOfEach.name}</td>
						<td>
						   <c:choose>
     						  <c:when  test="${productTypeOfEach.imageUrl==null||productTypeOfEach.imageUrl==''}">
         					    无图片
      						  </c:when>
     						  <c:otherwise>
         					    有图片
      					      </c:otherwise>
    					   </c:choose>
						 </td>
						<td>
						    <c:choose>
     						  <c:when  test="${productTypeOfEach.linkUrl==null||productTypeOfEach.linkUrl==''}">
         					    无
      						  </c:when>
     						  <c:otherwise>
         					   ${productTypeOfEach.linkUrl}
      					      </c:otherwise>
    					   </c:choose>
    					</td>
						<td>${productTypeOfEach.orderNum}</td>
						<td>${productTypeOfEach.productNumber}</td>
						<td><a href="<%=basePath%>admin/producttype/toUpdateProductType?id=${productTypeOfEach.id}"class="tablelink">修改</a>
							<a href="#" name="deleteProductType" alt="${productTypeOfEach.id}" class="tablelink"> 删除</a>
						</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>


		<div class="pagin">
			<div class="message">
				共<i class="blue">${requestScope.list.size()}</i>条记录
			</div>
		</div>

	</div>
</body>
</html>
