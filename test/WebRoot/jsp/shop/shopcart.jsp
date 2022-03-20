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
<link href="resources/css/shopcart.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="wrap">
		<iframe src="header.html" style="height: 118px;"></iframe>
		<div class="content">
			<div class="thead">
				<em class="th1">购物车</em>
				<em class="th2">单价</em>
				<em class="th3">数量</em>
				<em class="th4">总价</em>
				<em class="th5">操作</em>
			</div>
			<div class="tbody">
				<div class="tr">
					<span class="td td1">
						<input type="checkbox" class="check">
					</span>
					<span class="td td2">
						<a href="shopdetial.html"><img alt="" src="resources/images/foods/41.jpg"></a>
						<span>干煸四季豆</span>
					</span>
					<span class="td td3">
						<span>15.00</span>
						<b>12.00</b>
					</span>
					<span class="td td4">
						<div class="input-wrap">
							<button class="sub">-</button>
							<input type="text" class="number" value="1">
							<button class="add">+</button>
						</div>
					</span>
					<span class="td td5">
						<b>12.00</b>
					</span>
					<span class="td td6">
						<a href="#" class="delete">删除</a>
					</span>
				</div>
				<div class="tr">
					<span class="td td1">
						<input type="checkbox" class="check" checked="checked">
					</span>
					<span class="td td2">
						<a href="shopdetial.html"><img alt="" src="resources/images/foods/41.jpg"></a>
						<span>干煸四季豆</span>
					</span>
					<span class="td td3">
						<span>15.00</span>
						<b>12.00</b>
					</span>
					<span class="td td4">
						<div class="input-wrap">
							<button class="sub">-</button>
							<input type="text" class="number" value="1">
							<button class="add">+</button>
						</div>
					</span>
					<span class="td td5">
						<b>12.00</b>
					</span>
					<span class="td td6">
						<a href="#" class="delete">删除</a>
					</span>
				</div>
				<div class="tr">
					<span class="td td1">
						<input type="checkbox" class="check" checked="checked">
					</span>
					<span class="td td2">
						<a href="shopdetial.html"><img alt="" src="resources/images/foods/41.jpg"></a>
						<span>干煸四季豆</span>
					</span>
					<span class="td td3">
						<span>15.00</span>
						<b>12.00</b>
					</span>
					<span class="td td4">
						<div class="input-wrap">
							<button class="sub">-</button>
							<input type="text" class="number" value="1">
							<button class="add">+</button>
						</div>
					</span>
					<span class="td td5">
						<b>12.00</b>
					</span>
					<span class="td td6">
						<a href="#" class="delete">删除</a>
					</span>
				</div>
				<div class="tr">
					<span class="td td1">
						<input type="checkbox" class="check" checked="checked">
					</span>
					<span class="td td2">
						<a href="shopdetial.html"><img alt="" src="resources/images/foods/41.jpg"></a>
						<span>干煸四季豆</span>
					</span>
					<span class="td td3">
						<span>15.00</span>
						<b>12.00</b>
					</span>
					<span class="td td4">
						<div class="input-wrap">
							<button class="sub">-</button>
							<input type="text" class="number" value="1">
							<button class="add">+</button>
						</div>
					</span>
					<span class="td td5">
						<b>12.00</b>
					</span>
					<span class="td td6">
						<a href="#" class="delete">删除</a>
					</span>
				</div>
				<div class="tr">
					<span class="td td1">
						<input type="checkbox" class="check" checked="checked">
					</span>
					<span class="td td2">
						<a href="shopdetial.html"><img alt="" src="resources/images/foods/41.jpg"></a>
						<span>干煸四季豆</span>
					</span>
					<span class="td td3">
						<span>15.00</span>
						<b>12.00</b>
					</span>
					<span class="td td4">
						<div class="input-wrap">
							<button class="sub">-</button>
							<input type="text" class="number" value="1">
							<button class="add">+</button>
						</div>
					</span>
					<span class="td td5">
						<b>12.00</b>
					</span>
					<span class="td td6">
						<a href="#" class="delete">删除</a>
					</span>
				</div>
			</div>
			<div class="tfoot">
				<em class="tf tf1">
					<input type="checkbox" class="checks">
				</em>
				<em class="tf tf2">
					<a href="#" class="check-all">全选</a>
					<a href="#" class="check-delete">删除</a>
				</em>
				<em class="tf tf3">
					&nbsp;
				</em>
				<em class="tf tf4">
					已选择<span class="numbers">4</span>件&nbsp;&nbsp;&nbsp;&nbsp;合计：<span class="amounts">￥48.00</span>
				</em>
				<em class="tf tf5">
					<button class="btn-submit">结算</button>
				</em>
			</div>
			<hr color="#d2364c">
		</div>
		<iframe src="footer.html" style="height: 120px;"></iframe>
	</div>
	<script type="text/javascript">
		// 获取全选复选框
		var checks = document.getElementsByClassName("checks");
		// 获取全选超链接a
		var checkall = document.getElementsByClassName("check-all");
		// 获取全部tr里面的复选框
		var check = document.getElementsByClassName("check");
		// 全选点击事件
		function fn_checkall() {
			alert(checks);
			if(checks.checked) {
				check.checked = true;
			} else {
				check.checked = false;
			}
		}
		// 绑定全选事件
		checks.onclick = function() {
			fn_checkall();
		}
		checkall.onclick = function() {
			fn_checkall();
		}
		// 获取遍历tr行数据
		var trs = document.getElementsByClassName("tr");
		for(var i = 0; i < trs.length; i++) {
			trs[i].getElementsByClassName('sub').onclick = function() {
				var input = this.parentNode.getElementsByClassName('number');
				var num = Number(input.value || '0');
				if(num == 1) {
					num = 1;
				} else {
					num = num - 1;
				}
				alert(num);
			};
		}
	</script>
</body>
</html>