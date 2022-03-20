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
	
		$("a[name='deleteAdmin']").click(function() {
			 if(window.confirm('确定要删除吗？删除后无法恢复')){
			     var userId=$(this).attr("alt");
	             window.location.href="<%=basePath%>admin/adminmanage/doDeleteAdmin?userId="+userId; 
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
			    <a href="<%=basePath%>admin/adminmanage/toAddAdmin">
				<li class="click">
				   <span>
				      <img src="<%=basePath%>jsp/admin/images/t01.png" />
				   </span>添加管理账户
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
					<th style="width:4%;"><input name="" type="checkbox" value="" />
					</th>
					<th style="width:30%;">账户名</th>
					<th style="width:30%;">姓名</th>
					<th style="width:26%;">创建时间</th>
					<th style="width:10%;">操作</th>
				</tr>
			</thead>
			<tbody>


				<c:forEach var="adminOfEach" items="${requestScope.list}">
					<tr>
						<td><input name="" type="checkbox"
							value="${adminOfEach.userId}" /></td>
						<td>${adminOfEach.username}</td>
						<td>${adminOfEach.screenName}</td>
						<td>${adminOfEach.createTime}</td>
						<td><a href="<%=basePath%>admin/adminmanage/toUpdateAdmin?userId=${adminOfEach.userId}"class="tablelink">修改</a>
							<a href="#" name="deleteAdmin" alt="${adminOfEach.userId}" class="tablelink"> 删除</a>
						</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>


		<div class="pagin">
			<div class="message">
				共<i class="blue">${requestScope.list.size()}</i>条记录
			</div>
			<!-- <ul class="paginList">
        <li class="paginItem"><a href="javascript:;"><span class="pagepre"></span></a></li>
        <li class="paginItem"><a href="javascript:;">1</a></li>
        <li class="paginItem current"><a href="javascript:;">2</a></li>
        <li class="paginItem"><a href="javascript:;">3</a></li>
        <li class="paginItem"><a href="javascript:;">4</a></li>
        <li class="paginItem"><a href="javascript:;">5</a></li>
        <li class="paginItem more"><a href="javascript:;">...</a></li>
        <li class="paginItem"><a href="javascript:;">10</a></li>
        <li class="paginItem"><a href="javascript:;"><span class="pagenxt"></span></a></li>
        </ul> -->
		</div>


		<div class="tip">
			<div class="tiptop">
				<span>提示信息</span><a></a>
			</div>

			<div class="tipinfo">
				<span><img src="<%=basePath%>jsp/admin/images/ticon.png" />
				</span>
				<div class="tipright">
					<p>是否确认对信息的修改 ？</p>
					<cite>如果是请点击确定按钮 ，否则请点取消。</cite>
				</div>
			</div>

			<div class="tipbtn">
				<input name="" type="button" class="sure" value="确定" />&nbsp; <input
					name="" type="button" class="cancel" value="取消" />
			</div>

		</div>




	</div>
</body>
</html>
