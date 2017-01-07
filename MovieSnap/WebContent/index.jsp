<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<!--
	作者：459756123@qq.com
	时间：2016-08-06
	描述：以下标记是告诉浏览器，当前网页使用了html5技术！
-->
<!DOCTYPE html>
<html lang="zh-CN">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!--
        	作者：459756123@qq.com
        	时间：2016-08-06
        	描述：当前网页在 移动设备上，不可以 缩放
        -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>电影抢票系统</title>
<!-- Bootstrap -->
<link href="bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="bootstrap/3.3.4/js/jquery-1.11.0.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="bootstrap/3.3.4/js/bootstrap.min.js"></script>

<body>

	<div id="" class="container-fluid">
	
		<div class="row">
  			<div class="col-md-2"></div>
  			<div class="col-md-8">
  				<div class="jumbotron">
  					<center>
  						<h1>电影票抢票</h1>
  					</center>
  				</div>
  				</div>
  				<div class="col-md-2"></div>
  		</div>
  			<div class="row" id="login">
  			<div class="col-md-2"></div>
  			<div class="col-md-7">
  				<form class="form-horizontal" role="form" id="" 
									 action="login.do" method="post">
										<div class="form-group" id="inputemail">
											<label for="inputEmail3" class="col-sm-2 control-label">Email</label>
											<div class="col-sm-10">
												<input type="email" class="form-control" name="inputEmail3"id="inputEmail3"
												
													placeholder="xxx@xx.com">
											</div>
										</div>
										<div class="form-group" id="sign">
											<div class="col-sm-offset-2 col-sm-10">
												<button type="submit" class="btn btn-default" id="submit">Sign
													in</button>
											</div>
										</div>
									</form>
  				</div>
  				<div class="col-md-2"></div>
  		</div>
  		
  			
	</div>

</body>
<script>
	$(document).ready(function(){
		$("#login").hide().slideDown(3000);
	});
</script>
</html>