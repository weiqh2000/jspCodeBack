<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'dologin.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	
	<%
		String username = request.getParameter("username");
		String userpass = request.getParameter("userpass");
		if(username.equals("admin") && userpass.equals("123456")){
			session.setAttribute("admin","韦佗");
			response.sendRedirect("main.jsp");
		}else{
			out.print("登录失败,三秒后跳转回登录界面");
			response.setHeader("refresh","3;url=login.jsp");
		}
	 %>
	 
  </body>
</html>
