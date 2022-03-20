<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>网站后台管理系统HTML模板--模板之家 www.cssmoban.com</title>
<link href="<%=basePath %>jsp/admin/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath %>jsp/admin/js/jquery.js"></script>

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
    <li><a href="<%=basePath %>/jsp/admin/index.jsp">首页</a></li>
    <li><a href="#">数据表</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    
    <div class="tools">
    
    	<ul class="toolbar">
        <a href="<%=basePath %>admin/adminmanage/toAddAdmin"><li><span><img src="<%=basePath %>jsp/admin/images/t01.png" /></span>添加</li></a>
        <li><span><img src="<%=basePath %>jsp/admin/images/t02.png" /></span>修改</li>
        <li class="click"><span><img src="<%=basePath %>jsp/admin/images/t03.png" /></span>删除</li>
        <li><span><img src="<%=basePath %>jsp/admin/images/t04.png" /></span>统计</li>
        </ul>
        
        <ul class="toolbar1">
        <li><span><img src="<%=basePath %>jsp/admin/images/t05.png" /></span>设置</li>
        </ul>
    
    </div>
    
    
    <table class="tablelist">
    	<thead>
    	<tr>
        <th><input name="" type="checkbox" value="" checked="checked"/></th>
        <th>用户编号<i class="sort"><img src="<%=basePath %>jsp/admin/images/px.gif" /></i></th>
        <th>用户名</th>
        <th>用户密码</th>
        <th>昵称</th>
        <th>创建时间</th>
        <th>是否审核</th>
        <th>操作</th>
        </tr>
        </thead>
        
        <tbody>
        
        <c:forEach var="adminOfEach" items="${requestScope.admins}">
        <tr>
        <td><input name="" type="checkbox" value="" /></td>
        <td>${adminOfEach.userId}</td>
        <td>${adminOfEach.username}</td>
        <td>${adminOfEach.userpass}</td>
        <td>${adminOfEach.screenName}</td>
        <td>${adminOfEach.createTime}</td>
        <td>已审核</td>
        <td><a href="<%=basePath %>admin/adminmanage/toEditddAdmin?userId=${adminOfEach.userId}" class="tablelink" >修改</a>     <a href="#" class="tablelink" onclick="deleteAdmin(${adminOfEach.userId})" > 删除</a></td>
        </tr> 
        </c:forEach>
              
        </tbody>
    </table>
    
   
    <div class="pagin">
    	<div class="message">共<i class="blue">1256</i>条记录，当前显示第&nbsp;<i class="blue">2&nbsp;</i>页</div>
        <ul class="paginList">
        <li class="paginItem"><a href="<%=basePath %>jsp/admin/javascript:;"><span class="pagepre"></span></a></li>
        <li class="paginItem"><a href="<%=basePath %>jsp/admin/javascript:;">1</a></li>
        <li class="paginItem current"><a href="<%=basePath %>/javascript:;">2</a></li>
        <li class="paginItem"><a href="<%=basePath %>jsp/admin/javascript:;">3</a></li>
        <li class="paginItem"><a href="<%=basePath %>jsp/admin/javascript:;">4</a></li>
        <li class="paginItem"><a href="<%=basePath %>jsp/admin/javascript:;">5</a></li>
        <li class="paginItem more"><a href="<%=basePath %>jsp/admin/javascript:;">...</a></li>
        <li class="paginItem"><a href="<%=basePath %>jsp/admin/javascript:;">10</a></li>
        <li class="paginItem"><a href="<%=basePath %>jsp/admin/avascript:;"><span class="pagenxt"></span></a></li>
        </ul>
    </div>
    
    
    <div class="tip">
    	<div class="tiptop"><span>提示信息</span><a></a></div>
        
      <div class="tipinfo">
        <span><img src="<%=basePath %>jsp/admin/images/ticon.png" /></span>
        <div class="tipright">
        <p>是否确认对信息的修改 ？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
        
        <div class="tipbtn">
        <input name="" type="button"  class="sure" value="确定" />&nbsp;
        <input name="" type="button"  class="cancel" value="取消" />
        </div>
    
    </div>
    
    
    
    
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
