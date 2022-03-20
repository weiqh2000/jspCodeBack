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
<title>${requestScope.product.name}</title>
<link href="<%=basePath%>jsp/shop/resources/css/shopdetail.css" rel="stylesheet" type="text/css">
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
	        
	        
            //当数量输入框失去焦点时，用于手动输入数量的验证
            $("input[name='numberOfSale']").blur(function(){
               var number= $("input[name='numberOfSale']").attr('value');
               var stock =${requestScope.product.number};
               if(number>stock ){//不能超过库存
                   $(this).attr('value',stock); 
               }else if(number<1){
                    $(this).attr('value',1); 
               }      
            });
            
            //当点击+,数量+1,修改购物车中的指定商品
            $("button[name='toAddCart']").click(function() {    
                var value= $("input[name='numberOfSale']").attr('value');
                var number = Number(value);                
                number+=1;
                if(number>0){
                     var stock =${requestScope.product.number};
                     if(number<=stock ){//不能超过库存
                           $("input[name='numberOfSale']").attr('value',number); 
                     }           
                }else{
                    number=1;
                    $("input[name='numberOfSale']").attr('value',number);
                }
                return false;//保持位置不变
            });    
            
            
             //当点击-,数量-1,修改购物车中的指定商品
            $("button[name='toReduceCart']").click(function() {
                var value= $("input[name='numberOfSale']").attr('value');
                var number = Number(value);                
                number-=1;
                if(number>0){
                    $("input[name='numberOfSale']").attr('value',number);                  
                }else{
                    number=1;
                    $("input[name='numberOfSale']").attr('value',number);
                }
                return false;//保持位置不变
            });
            
            //当点击放入购物车时
            $("button[name='putCart']").click(function() {
                var value= $("input[name='numberOfSale']").attr('value');
                var number = Number(value);      
                var productId=${requestScope.product.id};      
                if(number>0){
                    location.href="<%=basePath%>shop/cart/doPutCart?productId="+productId+"&number="+number; 
                }
                return false;
            });
 });
</script>

</head>
<body>
	<div class="wrap">
		<iframe src="<%=basePath%>jsp/shop/header.jsp" style="height: 168px;"></iframe>
		<div class="content">
			<div class="block">
				<div class="crumb">
					<ul>
						<li><a href="<%=basePath%>shop/index">首页</a></li>
						<li><span>/</span></li>
						<li><a id="types" href="<%=basePath%>shop/producttype?productTypeId=${requestScope.product.productType.id}">${requestScope.product.productType.name}</a></li>
						<li><span>/</span></li>
						<li><a id="lists" href="#">${requestScope.product.name}</a></li>
					</ul>
				</div>
				<div class="block-wrap">
					<h3>${requestScope.product.name}</h3>
					<div>
						<div class="images">
							<div class="img-wrap">
								<img src="${requestScope.product.picUrl}">
							</div>
							<!-- <ul>
								<li><a class="item" href="shopdetial.html"><img alr="" src="resources/images/foods/01.jpg"></a></li>
								<li><a class="item" href="shopdetial.html"><img alr="" src="resources/images/foods/01.jpg"></a></li>
								<li><a class="item" href="shopdetial.html"><img alr="" src="resources/images/foods/01.jpg"></a></li>
								<li><a class="item" href="shopdetial.html"><img alr="" src="resources/images/foods/01.jpg"></a></li>
							</ul> -->
						</div>
						<div class="detials">
							<ul>
								<li class="hot">优惠活动：暂无</li>
								<li>促销价：<em class="hot">${requestScope.product.price}</em></li>
								<li>
									<div class="item-wrap">
										<div class="item">原价：<span>${requestScope.product.originalPrice}</span></div>
										<div class="item">累计销量：${requestScope.product.totalNumberOfOrder}份<!-- 累计销量：1320 份 --></div>
									</div>
								</li>
								<li class="detial-wrap">
									<label>详情：</label>
									<div class="detial">
										${requestScope.product.description}
									</div>
								</li>
								<li class="form-wrap">
									<form>
										数量：
										<div class="input-wrap">
											<button name="toReduceCart" class="sub">-</button>
											<input name="numberOfSale" type="text" class="number" value="1">
											<button name="toAddCart" class="add">+</button>
										</div> 
										库存<span class="numbers">${requestScope.product.number}</span>份
										<div class="btn-wrap">
											<button class="buy" disabled="true">立刻购买</button>
											<button name="putCart" class="add">加入购物车</button>
										</div>
									</form>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<hr color="#e3e3e2">
		<iframe src="<%=basePath%>jsp/shop/footer.html" style="height: 120px;"></iframe>
	</div>
</body>
</html>