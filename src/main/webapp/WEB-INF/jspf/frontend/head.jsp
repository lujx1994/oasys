<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String id = (session.getAttribute("id") != null)
			? ("?id=" + session.getAttribute("id"))
			: "";
%>
<!DOCTYPE html>
<html lang="zh">
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>呀诺达自动报表系统</title>

<!-- Bootstrap -->
<link href="bootstrap-3.3.4-dist/css/bootstrap.min.css" rel="stylesheet">
<link href="css/jumbotron.css" rel="stylesheet">
<script src="bootstrap-3.3.4-dist/js/jquery.min.js"></script>
<script src="bootstrap-3.3.4-dist/js/bootstrap.min.js"></script>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
<script type="text/javascript">
	$(document).ready(function() {
		$.ajax({
			type : 'post',
			url : 'workflow/countworkflow.do',
			dataType : 'text',
			success : function(data, status) {
				$(".badge").text(data);
				/* if(data.status){
					$(".badge").text(data);
				}else{
					$(".badge").text(0);
				} */
			}
		});
	})
</script>

</head>
<body>
	<div class="nav navbar-fixed-top" role="navigation">
		<nav class="navbar navbar-default">
			<div class="container">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target=".navbar-collapse">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="index.jsp">呀诺达自动报表系统</a>
				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li><a href="index.jsp">主页</a></li>
						<li><a href="workflow/pending.do">审批<span class="badge"></span></a></li>
						<li><a href="workform/nworkform/grid.do">申请</a></li>
						<!-- <li><a href="frontend/modifydelegate">委托</a></li> -->
						<!-- <li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">查询 <span class="caret"></span>
						</a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="query/main.do">按时间查询</a></li>
								<li><a href="frontend/rfinishedflow">已审批查询</a></li>
								<li><a href="application/all.do">已申请查询</a></li>
								<li class="divider"></li>
								<li><a href="frontend/rannouncement">公告查询</a></li>
							</ul></li> -->
						<!-- <li><a href="frontend/rdepartmentflow">报表分析</a></li>
						<li><a href="backend/lsysteminfo">进入管理平台</a></li> -->
					</ul>
					<%
						String checkLogin = "登入";
						if (request.getAttribute("checkLogin") != null) {
							checkLogin = (String) request.getAttribute("checkLogin");
						}
						if (session.getAttribute("id") != null) {
							checkLogin = "登出";
					%>
					<ul class="nav navbar-nav navbar-right">
						<li><a href="/oasys/user/center.do">个人中心</a></li>
						<li><a href="/oasys/user/logout.do">退出登录</a></li>
					</ul>
					<%
						} else {
					%>
					<ul class="nav navbar-nav navbar-right">
						<li><a href="/oasys/login.jsp">请登录</a></li>
					</ul>
					<%
						}
					%>
				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid -->
		</nav>
	</div>