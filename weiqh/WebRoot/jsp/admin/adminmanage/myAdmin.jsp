<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="<%=basePath%>jsp/admin/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="<%=basePath%>jsp/admin/js/jquery.js"></script>
<script>
 //
        function alert_myMessage(){
           var message="${requestScope.myMessage}";
           if(message!=""){
              alert(message);
           }
        }
        
         $(document).ready(function(){
             //
	        alert_myMessage();
	     });

</script>
</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">修改基本信息</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>修改基本信息</span></div>
    <form action="<%=basePath%>admin/adminmanage/myAdmin" meta="post">
    <input name="userId" type="hidden" value="${sessionScope.admin.userId}">
    <ul class="forminfo">
   	 <li><label>*账户名</label><input name="username" type="text" class="dfinput" value="${adminBean.username}"></li>
   	 <li><label>*网名</label><input name="screenName" type="text" class="dfinput" value="${adminBean.screenName}"></li>
   	 <li><label>&nbsp;</label><input name="" type="submit" class="btn" value="确认修改"></li>
    </ul>
    </form>
    
    </div>
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	
	function deleteAdmin(userId){
		var r = confirm("确认是否删除");
		if(r == true){
			var url = "<%=basePath%>/admin/adminmanage/deleteAdmin?userId="+userId;
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
