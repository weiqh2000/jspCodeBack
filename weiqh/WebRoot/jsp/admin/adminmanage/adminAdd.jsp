<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="<%=basePath %>jsp/admin/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="<%=basePath %>/jsp/admin/index.jsp">首页</a></li>
    <li><a href="#">表单</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>基本信息</span></div>
    <form method="get" action= "<%=basePath %>admin/adminmanage/addAdmin">
    <ul class="forminfo">
    <li><label>账户名</label><input name="username" type="text" id="username" class="dfinput" /><i>标题不能超过30个字符</i></li>
    <li><label>网&nbsp;&nbsp;&nbsp;名</label><input name="screenName" type="text" id="screenName" class="dfinput" /><i>多个关键字用,隔开</i></li>
    <li><label>密&nbsp;&nbsp;&nbsp;码</label><input name="userpass" type="text" id="userpass" class="dfinput" /><i>多个关键字用,隔开</i></li>
    <li><label>&nbsp;</label><input name="" type="submit" class="btn" value="确认保存"/></li>
    </ul>
    </form>
    
    </div>
    
</body>
</html>
