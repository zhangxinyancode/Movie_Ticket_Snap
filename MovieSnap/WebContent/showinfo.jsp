<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>



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
		<title>查看已经登记的学生信息</title>
		<!-- Bootstrap -->
		<link href="bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script src="bootstrap/3.3.4/js/jquery-1.11.0.js"></script>
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script src="bootstrap/3.3.4/js/bootstrap.min.js"></script>

		<body>

		<div id="" class="container-fluid">
			<div class="row">
				<div id="" class="col-md-1">

				</div>
				<div class="col-md-10">
					<div class="panel panel-danger">
						<div class="panel-heading">
							<div class="row">
								<div class="col-md-6">
									<h1>抢票列表</h1>
								</div>
								<div class="col-md-6" id="username">
									<h3><%=request.getSession().getAttribute("username") %></h3>
								</div>
							</div>

						</div>
						<div class="panel-body panel-success">
							<div class=" table-hover table-bordered table-striped">
								<table class="table" id="tab">
									<thead>
										<tr>
											<th></th>
											<th>名称</th>
											<th>余票</th>
											<th>开始时间</th>
											<th>结束时间</th>
											<th>活动进展</th>
											
											
										</tr>

									</thead>
									<tbody class="tbody">
										
									</tbody>

								</table>
							</div>

						</div>
						<div class="panel-footer">

						</div>
					</div>
				</div>
				<div id="" class="col-md-1">

				</div>
			</div>

		</div>
    <script>
  var fun = function(){ 
	  var xmlhttp;
	  //alert(1);
	  if (window.XMLHttpRequest)
	    {// code for IE7+, Firefox, Chrome, Opera, Safari
	    xmlhttp=new XMLHttpRequest();
	    }
	  else
	    {// code for IE6, IE5
	    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	    }
	  xmlhttp.onreadystatechange=function()
	    {
	    if (xmlhttp.readyState==4 && xmlhttp.status==200)
	      {
	      $(".tbody").empty();
	      var arr = eval(xmlhttp.responseText);
	      var len = arr.length;
	      var str = "";
	      for(var i =0 ;i<len;i++){
	    	  str = "<tr>";
	    	 // alert(arr[i].name);
	          str+="<td colspan='2'>"+arr[i].name+"</td>";
	          str+="<td>"+arr[i].num+"</td>";
	          str+="<td>"+arr[i].starttime+"</td>";
	          str+="<td>"+arr[i].endtime+"</td>";
	          str+="<td><button type='button' class='btn btn-default' id='submit'>开始抢购</button></td>";
	    	  str+="</tr>";
// 	    	  alert(str);
	    	  $(".tbody").append(str);
	      }
	      $(".btn").click(function(){
	    	  alert("asd");
	     	 // var index=$(this).closest("tr").index();
	     	 var moviename= $(this).closest("tr").find("td").eq(0).text();
	     	  //alert(value);
	     		var xmlhttp1;
	     		
	     		if (window.XMLHttpRequest)
	     	    {// code for IE7+, Firefox, Chrome, Opera, Safari
	     	    xmlhttp1=new XMLHttpRequest();
	     	    }
	     	  else
	     	    {// code for IE6, IE5
	     	    xmlhttp1=new ActiveXObject("Microsoft.XMLHTTP");
	     	    }
	     	  xmlhttp1.onreadystatechange=function()
	     	    {
	     	    if (xmlhttp1.readyState==4 && xmlhttp1.status==200)
	     	      {
	     	    	alert("抢购成功");
	     	    	};
	     	      }
	     	 xmlhttp1.open("GET","/MovieSnap/send.do?username="+$("#username").text()+"&moviename="+moviename,true);
  	 	  	xmlhttp1.send();
	     		
	     	});
	      }
	    }
	  xmlhttp.open("GET","/MovieSnap/show.do",true);
	  xmlhttp.send();
	  //setTimeout("fun()",3000);
   };
  
  fun();
  
 
    </script>
	</body>
</html>