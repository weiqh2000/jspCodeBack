﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>

<script type="text/javascript">
$(function(){	
	//导航切换
	$(".menuson li").click(function(){
		$(".menuson li.active").removeClass("active")
		$(this).addClass("active");
	});
	
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		$('dd').find('ul').slideUp();
		if($ul.is(':visible')){
			$(this).next('ul').slideUp();
		}else{
			$(this).next('ul').slideDown();
		}
	});
})	
</script>


</head>

<body style="background:#f0f9fd;">
	<div class="lefttop"><span></span>通讯录</div>
    
    <dl class="leftmenu">
        
    <dd>
    <div class="title">
    <span><img src="images/leftico01.png" /></span>管理员模块
    </div>
    	<ul class="menuson">
        <li class="active"><cite></cite><a href="index.jsp" target="rightFrame">首页模版</a><i></i></li>
        <li><cite></cite><a href="<%=basePath %>admin/adminmanage/showAdmins" target="rightFrame">管理员列表</a><i></i></li>
        <li><cite></cite><a href="<%=basePath %>admin/pageshow/adminPageShow" target="rightFrame">管理员列表(带分页)</a><i></i></li>
        <li><cite></cite><a href="imgtable.jsp" target="rightFrame">图片数据表</a><i></i></li>
        <li><cite></cite><a href="<%=basePath %>admin/adminmanage/toAddAdmin" target="rightFrame">添加编辑</a><i></i></li>
        <li><cite></cite><a href="imglist.jsp" target="rightFrame">图片列表</a><i></i></li>
        <li><cite></cite><a href="imglist1.jsp" target="rightFrame">自定义</a><i></i></li>
        <li><cite></cite><a href="tools.jsp" target="rightFrame">常用工具</a><i></i></li>
        <li><cite></cite><a href="filelist.jsp" target="rightFrame">信息管理</a><i></i></li>
        <li><cite></cite><a href="tab.jsp" target="rightFrame">Tab页</a><i></i></li>
        <li><cite></cite><a href="error.jsp" target="rightFrame">404页面</a><i></i></li>
        <li><cite></cite><a href="<%=basePath %>TCP" target="rightFrame">TCP</a><i></i></li>
        <li><cite></cite><a href="<%=basePath %>admin/productManage/ProductPage" target="rightFrame">产品管理(带分页)</a><i></i></li>
        <li><cite></cite><a href="<%=basePath %>admin/productTypeManage/ProductTypePage" target="rightFrame">产品分类管理(带分页)</a><i></i></li>
        <li><cite></cite><a href="<%=basePath %>admin/adminmanage/toMyPassword" target="rightFrame">修改密码</a><i></i></li>
        <li><cite></cite><a href="<%=basePath %>admin/adminmanage/toMyAdmin?Id=${sessionScope.admin.userId}" target="rightFrame">修改基本信息</a><i></i></li>
        <li><cite></cite><a href="<%=basePath %>admin/productTypemanage/showproductType" target="rightFrame">产品分类管理</a><i></i></li>
        <li><cite></cite><a href="<%=basePath %>admin/productmanage/showproduct" target="rightFrame">产品分类管理</a><i></i></li>
        <li><cite></cite><a href="<%=basePath %>admin/customer/customerManage" target="rightFrame">客户管理</a><i></i></li>
        </ul>    
    </dd>
        
    
<% 

String  url  =  "http://"  +  request.getServerName()  +  ":"  +  request.getServerPort()  +  request.getContextPath()+request.getServletPath().substring(0,request.getServletPath().lastIndexOf("/")+1);  
   
if(request.getQueryString()!=null) 
{   
    url+="?"+request.getQueryString();           
} 
System.out.println("path："+path); 
System.out.println("basePath："+basePath);    
System.out.println("URL："+url);    
System.out.println("URL参数："+request.getQueryString()); 
System.out.println(request.getServerName()+":"+request.getServerPort()+request.getRequestURI()+"?"+request.getQueryString());
 String url1 = request.getServerName()+":"+request.getServerPort()+request.getRequestURI();
%>
    
    
    <dd>
    <div class="title">
    <span><img src="images/leftico02.png" /></span>其他设置
    </div>
    <ul class="menuson">
        <li><cite></cite><a href="#">编辑内容</a><i></i></li>
        <li><cite></cite><a href="#">发布信息</a><i></i></li>
        <li><cite></cite><a href="#">档案列表显示</a><i></i></li>
        </ul>     
    </dd> 
    
    
    <dd><div class="title"><span><img src="images/leftico03.png" /></span>编辑器</div>
    <ul class="menuson">
        <li><cite></cite><a href="#">自定义</a><i></i></li>
        <li><cite></cite><a href="#">常用资料</a><i></i></li>
        <li><cite></cite><a href="#">信息列表</a><i></i></li>
        <li><cite></cite><a href="#">其他</a><i></i></li>
    </ul>    
    </dd>  
    
    
    <dd><div class="title"><span><img src="images/leftico04.png" /></span>日期管理</div>
    <ul class="menuson">
        <li><cite></cite><a href="#">自定义</a><i></i></li>
        <li><cite></cite><a href="#">常用资料</a><i></i></li>
        <li><cite></cite><a href="#">信息列表</a><i></i></li>
        <li><cite></cite><a href="#">其他</a><i></i></li>
    </ul>
    
    </dd>   
    
    </dl>
    <script type="text/javascript">
    
	
	</script>
</body>
</html>
