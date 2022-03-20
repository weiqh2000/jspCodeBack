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
<title>橙汁园餐厅 -- 登录</title>
<link href="<%=basePath%>jsp/shop/resources/css/signin.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=basePath%>jsp/admin/js/jquery.js"></script>
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
<div class="wrap signin">
	<div class="content">
		<div class="title">
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
		<div class="main">
			<div class="header">
				<a href="javascript:void(0);" onclick="alert('该功能建设中');" >注册</a>
			</div>
			<div class="logo">
				<div>
					<img alt="" src="<%=basePath%>jsp/shop/resources/images/signin-logo.png">
					<span>用户登陆</span>
				</div>
			</div>
			<form action="<%=basePath%>shop/login">
				<div class="input-wrap">
					<img alt="" src="<%=basePath%>jsp/shop/resources/images/signin-user.png">
					<input name="username" type="text" id="u" placeholder="请输入账户名">
				</div>
				<div class="input-wrap">
					<img alt="" src="<%=basePath%>jsp/shop/resources/images/signin-pwd.png">
					<input name="password" type="password" id="p" placeholder="请输入密码">
				</div>
				<!-- <div class="input-wrap">
					<img alt="" src="resources/images/signin-cpt.png">
					<input class="sort" type="text" id="c" placeholder="请输入验证码">
					<img class="signin-cpt" alt="" src="resources/images/signin-cpts.png">
				</div> -->
				<div class="input-wrap nobg">
					<button class="btn-inline lbtn" id="submit">登录</button>
				</div>
			</form>
		</div>
	</div>
	<div class="footer">
		<h1>订餐热线：0772-1234567</h1>
		<p>版权所有@ 2012-2019 南方橙汁园连锁有限公司   桂ICP备90887766号  桂餐证字2010110108002888<br>
地址：桂柳市文林区中文苑路1号橙汁园大厦B座111层11号</p>
	</div>
</div>
</html>