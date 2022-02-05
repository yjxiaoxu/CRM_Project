<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<html>
<head>
	<base href="<%=basePath%>">
<meta charset="UTF-8">
<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		if (window.top != window) {
			window.top.location = window.location;
		}
		//初始化页面
		$(function() {
			//获取用户提示框对象
			var userText = $("#loginAct");
			//获取密码提示框对象
			var passwordText = $("#loginPwd");
			//每次刷新自动清除登录账号文本框的内容
			userText.val("");
			//为登录账号文本框绑定自动获取焦点事件
			userText.focus();
			//当账号文本框或者密码文本框获取焦点时，将提示框中的内容清空
			userText.focus(function() {
				$("#msg").html("");
			});
			passwordText.focus(function() {
				$("#msg").html("");
			});
			//为登录按钮添加鼠标单击事件
			$("#loginBtn").click(function() {
				login();
			});
			//当按下回车键时登录
			$(window).keydown(function(event) {
				// alert(event.keyCode);
				if (event.keyCode == 13) {
					login();
				}
			});
		});
		function login() {
			//alert("登录账号！");
			//获取用户输入的账号和密码并去除前后空白
			var loginAct = $.trim($("#loginAct").val()); 	//账号
			var loginPwd = $.trim($("#loginPwd").val());	//密码
			/**
			 * 如果账号获取密码为空，提示账号和密码不能为空
			 */
			if (loginAct == "" || loginPwd == "") {
				$("#msg").html("账号或密码不能为空");
				//将账号文本框和密码文本框的信息清空
				return false;
			}
			//发送ajax请求
			$.ajax({
				url : "settings/user/login.do",
				data: {
					"loginAct" : loginAct,
					"loginPwd" : loginPwd
				},
				type: "post",
				dataType : "json",
				success : function(data) {
					/**
					 * data的返回值
					 * {"success":true/false,"exception":"?????"}
					 */
					//alert(data);
					if (data.success) {
						window.location.href = "workbench/index.jsp";
					} else {
						$("#msg").html(data.msg);
					}
				}
			});
		}
	</script>
</head>
<body>
	<div style="position: absolute; top: 0px; left: 0px; width: 60%;">
		<img src="image/IMG_7114.JPG" style="width: 100%; height: 90%; position: relative; top: 50px;">
	</div>
	<div id="top" style="height: 50px; background-color: #3C3C3C; width: 100%;">
		<div style="position: absolute; top: 5px; left: 0px; font-size: 30px; font-weight: 400; color: white; font-family: 'times new roman'">CRM &nbsp;<span style="font-size: 12px;">&copy;2020&nbsp;小许</span></div>
	</div>
	
	<div style="position: absolute; top: 120px; right: 100px;width:450px;height:400px;border:1px solid #D5D5D5">
		<div style="position: absolute; top: 0px; right: 60px;">
			<div class="page-header">
				<h1>登录</h1>
			</div>
			<form action="workbench/index.jsp" class="form-horizontal" role="form">
				<div class="form-group form-group-lg">
					<div style="width: 350px;">
						<input class="form-control" type="text" placeholder="用户名" id="loginAct">
					</div>
					<div style="width: 350px; position: relative;top: 20px;">
						<input class="form-control" type="password" placeholder="密码" id="loginPwd">
					</div>
					<div class="checkbox"  style="position: relative;top: 30px; left: 10px;">
						
							<span id="msg" style="color: red"></span>
						
					</div>
					<button type="button" class="btn btn-primary btn-lg btn-block"  style="width: 350px; position: relative;top: 45px;" id="loginBtn">登录</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>