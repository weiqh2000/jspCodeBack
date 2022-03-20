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
	        
	        //删除产品事件
	        $("a[name='deleteProduct']").click(function() {
			 if(window.confirm('确定要删除吗？删除后无法恢复')){
			     var productId=$(this).attr("alt");
	             window.location.href="<%=basePath%>admin/product/doDeleteProduct?id="+productId+"&page=${requestScope.pageObject.currentPage}"; 
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
    <li><a href="#">产品管理</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    
    <div class="tools">
    
    	<ul class="toolbar">
    	  <a href="<%=basePath%>admin/product/toAddproduct?page=${requestScope.pageObject.currentPage}">
       	   <li class="click">
              <span><img src="<%=basePath%>jsp/admin/images/t01.png" /></span>添加产品
           </li>
           </a>
           <li><span><img src="<%=basePath%>jsp/admin/images/t04.png" /></span>统计</li>
        </ul>
        
        <ul class="toolbar1">
        <li><span><img src="<%=basePath%>jsp/admin/images/t05.png" /></span>设置</li>
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
    <th style="width:8%;">是否上架</th>
    <th style="width:8%;">库存</th>
    <th style="width:8%;">点击数</th>
    <th>操作</th>
    </tr>
    </thead>
    
    <tbody>
    <c:set var="list" value="${requestScope.pageObject.list}"/>
    <c:forEach var="productOfEach" items="${list}">
    <tr>
    <td class="imgtd">
     <c:choose>
       <c:when  test="${productOfEach.picUrl==null||productOfEach.picUrl==''}">
            <img src="<%=basePath%>jsp/admin/images/img13.png" style="width:80px;height:60px;"/>
       </c:when>
       <c:otherwise>
            <img src="${productOfEach.picUrl}" style="width:80px;height:60px;"/>
       </c:otherwise>
      </c:choose>
    </td>
    <td>
       <a href="#">${productOfEach.name}</a>
       <p>发布时间：<fmt:formatDate value="${productOfEach.createTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></p>
       </td>
    <td>${productOfEach.productType.name}<p>ID: ${productOfEach.productType.id}</p></td>
    <td><i>${productOfEach.price}</i><p style="text-decoration:line-through;">${productOfEach.originalPrice}</p></td>
    <td>admin</td>
    <td>
      <c:choose>
       <c:when  test="${productOfEach.onSale==true}">
        	   上架中
       </c:when>
       <c:otherwise>
          <i>已下架</i>
       </c:otherwise>
      </c:choose>
    </td>
    <td>${productOfEach.number}</td>
    <td>${productOfEach.click}</td>
    <td><a href="<%=basePath%>admin/product/toUpdateProduct?id=${productOfEach.id}&page=${requestScope.pageObject.currentPage}" class="tablelink">修改</a>
		<a href="#" name="deleteProduct" alt="${productOfEach.id}" class="tablelink">删除</a>
	</td>
    </tr>
    </c:forEach>
    
    </tbody>
    
    </table>
    
    
    
    
    
   
    <div class="pagin">
    	<div class="message">
    	           共<i class="blue">${requestScope.pageObject.totalRecords}</i>条记录,当前显示第<i class="blue">${requestScope.pageObject.currentPage}</i>页,共<i class="blue">${requestScope.pageObject.maxPage}</i>页
    	</div>
        <ul class="paginList">
        <li class="paginItem"><a href="<%=basePath%>admin/product/productmanage?page=1">首页</a></li>
        <li class="paginItem"><a href="<%=basePath%>admin/product/productmanage?page=${requestScope.pageObject.previousPage}">上一页</a></li>
        <li class="paginItem"><a href="<%=basePath%>admin/product/productmanage?page=${requestScope.pageObject.nextPage}">下一页</a></li>
        <li class="paginItem"><a href="<%=basePath%>admin/product/productmanage?page=${requestScope.pageObject.maxPage}">尾页</a></li>
        </ul>
    </div>
    
    
      
    
    
    </div>
    
   
<script type="text/javascript">
	$('.imgtable tbody tr:odd').addClass('odd');
	</script>
</body>
</html>
