<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + 	request.getServerPort() + request.getContextPath() + "/";
%>

<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
	<meta charset="UTF-8">
	<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
	<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
	<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
	<script>
		$(function () {
			//页面加载完毕执行此处函数
			//1. 登录页面加载完毕后，光标聚焦到账号输入框
			$("#logAct").focus();
			//2. 为登录按钮绑定点击事件
			$("#loginbtn").click(function () {
				login();
			})
			//3. 为当前窗口绑定敲键盘事件，使敲键盘也能进行登录
			$(window).keydown(function (event) {
				if (event.keyCode == 13){
					login();
				}
			})
		})
		//封装好的自定义方法，写在$(function(){})外面
		function login() {
			var logAct = $("#logAct").val();
			var logPwd = $("#logPwd").val();
			if (logAct.trim() == "" || logPwd.trim() == "") {
				$("#msg").html("用户名和密码不能为空");
				return ;
			}
			$.ajax({
				url: "settings/user/login.do",
				data: {
					"logAct" : logAct,
					"logPwd" : logPwd
				},
				type: "post",
				dataType: "json",
				success: function (data) {
					if (data.success){
						//登录成功,跳转到主页
						window.location,href = "workbench/index.html";
					}
					else {
						//登录失败，显示错误信息
						$("msg").html(data.msg);
					}
				}
			})
		}
	</script>
</head>
<body>
	<div style="position: absolute; top: 0px; left: 0px; width: 60%;">
		<img src="image/IMG_7114.JPG" style="width: 100%; height: 90%; position: relative; top: 50px;">
	</div>
	<div id="top" style="height: 50px; background-color: #3C3C3C; width: 100%;">
		<div style="position: absolute; top: 5px; left: 0px; font-size: 30px; font-weight: 400; color: white; font-family: 'times new roman'">CRM &nbsp;<span style="font-size: 12px;">&copy;2017&nbsp;动力节点</span></div>
	</div>
	
	<div style="position: absolute; top: 120px; right: 100px;width:450px;height:400px;border:1px solid #D5D5D5">
		<div style="position: absolute; top: 0px; right: 60px;">
			<div class="page-header">
				<h1>登录</h1>
			</div>
			<form action="workbench/index.html" class="form-horizontal" role="form">
				<div class="form-group form-group-lg">
					<div style="width: 350px;">
						<input class="form-control" type="text" placeholder="用户名" name="logAct" id="logAct">
					</div>
					<div style="width: 350px; position: relative;top: 20px;">
						<input class="form-control" type="password" placeholder="密码" name="logPwd" id="logPwd">
					</div>
					<div class="checkbox"  style="position: relative;top: 30px; left: 10px;">
						
							<span id="msg" style="color: red"></span>
						
					</div>
					<button type="button" id="loginbtn" class="btn btn-primary btn-lg btn-block"  style="width: 350px; position: relative;top: 45px;">登录</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>