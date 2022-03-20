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
<title>橙汁园餐厅 -- 购物车</title>
<link href="<%=basePath%>jsp/shop/resources/css/shopcart.css" rel="stylesheet" type="text/css">
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
	        
	         //当点击结算按钮时
           $("button[name='submitOrders']").click(function() {
              ret1=confirm("确定提交订单吗？");
              if(ret1==true){
                    ret2=confirm("请问是否立即付款？");
                    if(ret2==true){
                       location.href = "<%=basePath%>shop/order/doSubmitOrders?tag=1";
                    }else{
                       location.href = "<%=basePath%>shop/order/doSubmitOrders?tag=2";
                    }
              }
              return false;       
           });
	        
            
 });
</script>


</head>
<body>
	<div class="wrap">
		<iframe src="<%=basePath%>jsp/shop/header.jsp" style="height: 118px;"></iframe>
		<div class="content">
	 	  <table style="width:100%;border-collapse:collapse;">
		    <tr style="background-color: #e3e3e2;height:40px;line-height:40px;">
		      <th style="width:40%;text-align:left;padding-left:5px;">订单信息</th>
		      <th>单价</th>
		      <th>数量</th>
		      <th>总价</th>
		    </tr>
		    <c:set var="list" value="${sessionScope.shoppingCart.list}"/>
		    <c:forEach var="shoppingCartItem" items="${list}" varStatus="status">
		    <tr style="text-align:center;vertical-align:middle;">
		       <td style="padding-left:5px;height:90px;line-height:90px;text-align:left;">
		          <a href="<%=basePath%>shop/product?productId=${shoppingCartItem.product.id}">
		             <img style="width:120px;height:80px;margin:5px 0;float:left;" src="${shoppingCartItem.product.picUrl}">
		          </a>
				  ${shoppingCartItem.product.name}
			   </td>
		       <td><span style="text-decoration:line-through;">${shoppingCartItem.product.originalPrice}</span><br/>
					<b>${shoppingCartItem.product.price}</b>
			   </td>
		       <td>
		           <input type="hidden" name="productId" value="${shoppingCartItem.product.id}"/>
				   ${shoppingCartItem.number}
			   </td>
		       <td><b>${shoppingCartItem.priceOfTotal}</b></td>
		    </tr>
		    </c:forEach>
		     <tr style="background-color: #e3e3e2;height:40px;line-height:40px;">
		      <td style="text-align:right;" colspan="4">
		        <em class="tf tf4">
					已选择<span>${sessionScope.shoppingCart.numberOfProduct}</span>件
					&nbsp;&nbsp;&nbsp;&nbsp;合计：<span style="color:#d2364c;margin:0 20px;">￥${sessionScope.shoppingCart.priceOfTotal}</span>
				</em>
			  </td>		    
		    </tr>
		  </table>		 
		  收货人姓名：${sessionScope.customer.name}<br/>
		  收货人地址：${sessionScope.customer.address}<br/>
		  收货人邮编：${sessionScope.customer.zip}<br/>
		  收货人电话：${sessionScope.customer.tel}<br/>
		   <hr color="#d2364c" style="height:1px;margin:5px 3px;">
		  <button name="submitOrders" style="float:right;clear:right;height: 48px;width:150px;background-color:#d2364c;color:#ffffff;border:0;">提交订单</button>
		  <button onclick="location.href ='<%=basePath%>jsp/shop/cart/shopcart.jsp'" style="float:right;margin-right:20px;height: 48px;width:150px;background-color:#d2364c;color:#ffffff;border:0;">返回购物车</button>
		</div>
		<iframe src="<%=basePath%>jsp/shop/footer.html" style="height: 120px;"></iframe>
	</div>
	
</body>
</html>