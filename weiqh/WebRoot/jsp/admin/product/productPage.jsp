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
  $('.tablelist tbody tr:odd').addClass('odd');//表格隔行变色
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
        
        <a href="<%=basePath %>go/toAddProduct"><li><span><img src="<%=basePath %>jsp/admin/images/t01.png" /></span>添加</li></a>
        <li><span><img src="<%=basePath %>jsp/admin/images/t02.png" /></span>修改</li>
        <li class="click"><span><img src="<%=basePath %>jsp/admin/images/t03.png" /></span>删除</li>
        <li><span><img src="<%=basePath %>jsp/admin/images/t04.png" /></span>统计</li>
        </ul>
        
        <ul class="toolbar1">
        <li><span><img src="<%=basePath %>jsp/admin/images/t05.png" /></span>设置</li>
        </ul>
    
    </div>
    
    
    <table class="imgtable">
    	<thead>
    	<tr>
        <th width="100px;">产品图片</th>
        <th style="width:25%;">产品名</th>
        <th style="width:10%;">产品分类</th>
        <th style="width:8%;">产品价格</th>
        <th style="width:10%;">发布人</th>
        <th style="width:8%;">是是否上架</th>
        <th style="width:8%;">库存</th>
        <th style="width:8%;">点击数</th>
        <th>操作</th>
        </tr>
        </thead>
        
        <tbody>
        
        <c:forEach var="productOfEach" items="${requestScope.products}">
        <tr>
        <td><img src="${productOfEach.picUrl}" style="width:80px;height:60px;" /></td>
        <td><a href="#">${productOfEach.name}</a><p>发布时间：${productOfEach.createTime}</p></td>
        <td>${productOfEach.productType.name}<p>ID: ${productOfEach.productTypeId}</p></td>
        <td><i>${productOfEach.price}</i><p style="text-decoration:line-through;">${productOfEach.originalPrice}</p></td>
        <td>${productOfEach.admin.screenName}</td>
        <td>${productOfEach.onSale_String}</td>
        <td>${productOfEach.number}</td>
        <td>${productOfEach.click}</td>
        <td><a href="<%=basePath %>admin/Product/toEditProduct?Id=${productOfEach.id}" class="tablelink" >修改</a>     <a href="#" class="tablelink" onclick="deleteAdmin(${productOfEach.id})" > 删除</a></td>
        </tr> 
        </c:forEach>
              
        </tbody>
        
    </table>
    
   
    <div class="pagin"><c:out value="${requestScope.page}"></c:out>
    	<div class="message">共<i class="blue"><c:out value="${requestScope.page.lineCount}"></c:out></i>条记录，
    	当前显示第&nbsp;<i class="blue">${requestScope.page.pageNow}&nbsp;</i>页，
    	共<i class="blue">&nbsp;${requestScope.page.pageCount}&nbsp;</i>页
    	</div>
        <ul class="paginList">
        <li class="paginItem"><a href="<%=basePath %>/go/toPageProduct?index=1">首页</a></li>
        <li class="paginItem"><a href="<%=basePath %>/go/toPageProduct?index=${requestScope.page.pageNow-1}">上一页</a></li>

        <li class="paginItem"><a href="<%=basePath %>/go/toPageProduct?index=${requestScope.page.pageNow+1}">下一页</a></li>
        <li class="paginItem"><a href="<%=basePath %>go/toPageProduct?index=${requestScope.page.pageCount}">尾页</a></li>
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
	
	function deleteAdmin(id){
		var r = confirm("确认是否删除");
		if(r == true){
			var url = "<%=basePath%>admin/go/doDelectProduct?id="+id;
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
