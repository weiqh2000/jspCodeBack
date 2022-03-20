<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="<%=basePath%>jsp/admin/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>jsp/admin/js/jquery.js"></script>
<script type="text/javascript">
$(document).ready(function(){
  $(".click").click(function(){
  $(".tip").fadeIn(200);
  });
  
  $(".tiptop a").click(function(){
  $(".tip").fadeOut(200);
});

  $(".sure").click(function(){
  $(".tip").fadeOut(100);
});

  $(".cancel").click(function(){
  $(".tip").fadeOut(100);
});

});

</script>
</head>


<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">客户管理</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    
    <div class="tools">
    
    	<ul class="toolbar">
    	  <a href="<%=basePath%>go/toAddCustomer">
       	   <li class="click">
              <span><img src="<%=basePath%>jsp/admin/images/t01.png" /></span>添加客户
           </li>
           </a>
           <li><span><img src="<%=basePath%>jsp/admin/images/t04.png" /></span>统计</li>
        </ul>
        
    </div>
    
    
    <table class="tablelist">
    
    <thead>
    <tr>
    <th style="width:4%;"><input name="" type="checkbox" value="" /></th>
    <th style="width:25%;">账户名</th>
    <th style="width:10%;">名字</th>
    <th style="width:15%;">电子邮箱</th>
    <th style="width:15%;">注册时间</th>
    <th style="width:10%;">订单数量</th>
    <th style="width:8%;">消费金额</th>
    <th>操作</th>
    </tr>
    </thead>
    
    <tbody>
<%--     <c:set var="list" value="${requestScope.pageObject.list}"/> --%>
    <c:forEach var="customerOfEach" items="${list}">
    <tr>
    <td><input name="" type="checkbox" value="${customerOfEach.id}"/></td>
    <td>${customerOfEach.username}</td>
    <td>${customerOfEach.name}</td>
    <td>${customerOfEach.email}</td>
    <td><fmt:formatDate value="${customerOfEach.createTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
    <td></td>
    <td></td>
    <td><a href="<%=basePath%>/go/toEditCustomer?Id=${customerOfEach.id}" class="tablelink">修改</a>
		<a href="#" class="tablelink" onclick="deleteAdmin(${customerOfEach.id})" > 删除</a></td>
	</td>
    </tr>
    </c:forEach>
    
    </tbody>
    
    </table>
    
    
    
    
    
   
<%--     <div class="pagin">
    	<div class="message">
    	           共<i class="blue">${requestScope.pageObject.totalRecords}</i>条记录,当前显示第<i class="blue">${requestScope.pageObject.currentPage}</i>页,共<i class="blue">${requestScope.pageObject.maxPage}</i>页
    	</div>
        <ul class="paginList">
        <li class="paginItem"><a href="<%=basePath%>admin/customer/customermanage?page=1">首页</a></li>
        <li class="paginItem"><a href="<%=basePath%>admin/customer/customermanage?page=${requestScope.pageObject.previousPage}">上一页</a></li>
        <li class="paginItem"><a href="<%=basePath%>admin/customer/customermanage?page=${requestScope.pageObject.nextPage}">下一页</a></li>
        <li class="paginItem"><a href="<%=basePath%>admin/customer/customermanage?page=${requestScope.pageObject.maxPage}">尾页</a></li>
        </ul>
    </div> --%>
 
    
    </div>
        <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	
	function deleteAdmin(Id){
		var r = confirm("确认是否删除");
		if(r == true){
			var url = "<%=basePath%>admin/customer/customerManageDelete?Id="+Id;
			location.href = url;
		};
	}
	
	
	
	
	function alertMessage(){
		var message = "${requestScope.message}";
		if(message != ''){//如果服务器返回的信息不为空
			alert(message);
		};
	};
	
	window.onload = function(){//每次页面加载时会弹出
		alertMessage();
	}
	
	</script>
</body>
</html>
