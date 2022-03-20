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
<title>无标题文档</title>
<link href="<%=basePath%>jsp/admin/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="<%=basePath%>jsp/admin/js/jquery.js"></script>
<script>
 //用于弹出窗口，将服务器返回的数据提交，本处用于账户提交后的状态
        function alert_myMessage(){
           var message="${requestScope.myMessage}";
           if(message!=""){
              alert(message);
           }
        }
        
         $(document).ready(function(){
             //用于弹出窗口，将服务器返回的数据提交，本处用于账户提交后的状态
	        alert_myMessage();
	     });

</script>
</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="<%=basePath%>admin/adminmanage/adminmanage">账户管理</a></li>
    <li><a href="#">账户编辑</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>账户编辑</span></div>
    <form action="<%=basePath%>admin/adminmanage/doUpdateAdmin">
        <input name="userId" type="hidden" value="${requestScope.admin.userId}"/>
    <ul class="forminfo">
    <li><label>*账户名</label><input name="username" type="text" class="dfinput" value="${requestScope.admin.username}"/></li>
    <li><label>*网名</label><input name="screenName" type="text" class="dfinput" value="${requestScope.admin.screenName}"/></li>
    <li><label>&nbsp;</label><input name="" type="submit" class="btn" value="确认修改"/></li>
    </ul>
    </form>
    
    </div>
</body>
</html>
