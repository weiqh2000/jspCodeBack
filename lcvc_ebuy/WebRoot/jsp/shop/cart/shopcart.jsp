<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
	        
              //当点击+,数量+1,修改购物车中的指定商品
            $("button[name='add']").click(function() {
                var id=$(this).attr('title');   
                var value= $("input[name='numberOfSale'][title="+id+"]").attr('value');
                var number = Number(value);                
                number+=1;
                if(number>0){
                   $(this).attr('href','#');
                    $("input[name='numberOfSale'][title="+id+"]").attr('value',number);
                    $("form[name="+id+"]").submit();                      
                }else{
                    number=1;
                    $(this).attr('value',number);
                    return false;    
                }
            });    
            
             //当点击-,数量-1,修改购物车中的指定商品
            $("button[name='sub']").click(function() {
                var id=$(this).attr('title');       
                var value= $("input[name='numberOfSale'][title="+id+"]").attr('value');
                var number = Number(value);                
                number-=1;
                if(number>0){
                   $(this).attr('href','#');
                   $("input[name='numberOfSale'][title="+id+"]").attr('value',number);
                   $("form[name="+id+"]").submit();                      
                }else{
                    number=1;
                    $(this).attr('value',number);
                    return false;    
                }
            });    
            
             //修改购物车中的指定商品 
            $("input[name='numberOfSale']").live("blur",function() {
                var id=$(this).attr('title');
                var value=$(this).attr('value');
                var number = Number(value);
                if(number<1){
                    number=1;
                    $(this).attr('value',number);
                    return false;
                }
                $("form[name="+id+"]").submit();        
             });    
             
                //删除购物车中的指定商品
           $("a[name='delete']").click(function(){
              ret1=confirm("确定从购物车中删除此商品吗？");
              if(ret1==true){
                   var a=$(this).attr('title');
                   $("form[name="+a+"]").attr("action","<%=basePath%>shop/cart/doDeleteCart");
                   $("form[name="+a+"]").submit();        
              }
              return false;       
           });  
           
           //当点击结算按钮时
           $("button[name='accountant']").click(function() {
               location.href = "<%=basePath%>shop/order/showOrder"
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
		      <th style="width:40%;text-align:left;padding-left:5px;">购物车</th>
		      <th>单价</th>
		      <th>数量</th>
		      <th>总价</th>
		      <th>操作</th>
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
		          <form name="${status.index}"  action="<%=basePath%>shop/cart/doUpdateCart" method="post">    
		           <input type="hidden" name="productId" value="${shoppingCartItem.product.id}"/>
		          <button name="sub" class="sub" title="${status.index}" >-</button>
				  <input name="numberOfSale" title="${status.index}" type="text" style="width:35px;text-align:center;" value="${shoppingCartItem.number}">
				  <button name="add" class="add" title="${status.index}" >+</button>
				  </form>
			   </td>
		       <td><b>${shoppingCartItem.priceOfTotal}</b></td>
		       <td><a href="#" name="delete" class="delete" title="${status.index}">删除</a></td>
		    </tr>
		    </c:forEach>
		     <tr style="background-color: #e3e3e2;height:40px;line-height:40px;">
		      <td style="text-align:right;" colspan="5">
		        <em class="tf tf4">
					已选择<span>${sessionScope.shoppingCart.numberOfProduct}</span>件
					&nbsp;&nbsp;&nbsp;&nbsp;合计：<span style="color:#d2364c;margin:0 20px;">￥${sessionScope.shoppingCart.priceOfTotal}</span>
				</em>
			  </td>		    
		    </tr>
		  </table>
		  <hr color="#d2364c" style="height:1px;margin:5px 3px;">
		  <c:choose>
    			   <c:when  test="${fn:length(list)>0}">
        	           <button name="accountant" style="float:right;clear:right;height: 48px;width:150px;background-color:#d2364c;color:#ffffff;border:0;">结算</button>
      	    	   </c:when>
     	    	   <c:otherwise>
      	             <a href="<%=basePath%>shop/index">购物车是空的哦，请先购物</a>
     	    	    </c:otherwise>
    	      </c:choose>
		 
		</div>
		<iframe src="<%=basePath%>jsp/shop/footer.html" style="height: 120px;"></iframe>
	</div>
	
</body>
</html>