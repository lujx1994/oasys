<%@ include file="/WEB-INF/jspf/frontend/head.jsp"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<!-- Example row of columns -->
	<div class="page-header">

		<div class="pull-left">
			<h2>
				申请报表<small><span class="label label-info">网格视图</span></small>
			</h2>
		</div>
		<div class="pull-right">
			<h2 id="gridview">
				<span class="glyphicon glyphicon-hand-right" aria-hidden="true"></span>
				<small><span class="label label-success">列表视图</span></small>
			</h2>
		</div>
		<div class="clearfix"></div>
	</div>
	<div class="row">
	<c:forEach items="${allForm}" var="form">
		<div class="col-sm-6 col-md-3">
			<div class="thumbnail">
				<img src="${form.image}" alt="Generic placeholder thumbnail">
				<div class="caption">
					<h3>${form.name}</h3>
					<p>这里可以介绍填写报表要求，和其他相关信息</p>
					<p>
						<a href="workflow/nworkflow/${form.id}.do?checked=0" class="btn btn-primary" role="button">申请</a>
				</div>
			</div>
		</div>
	</c:forEach>
	</div>

	<footer>
		<p>&copy; Yanoda 2015</p>
	</footer>
</div>
<!-- /container -->


<script>
	$(".list-group a:first-child").addClass("active");
	var typeid = $(".list-group a:first-child").data("typeid");
	$("tbody tr").hide();
	$("tr[data-typeid=\"" + typeid + "\"]").show();
	$(".list-group a").on("click", function() {
		var type_id = $(this).data("typeid");
		$("tbody tr").hide();
		$("tr[data-typeid=\"" + type_id + "\"]").show();
		$(this).siblings().removeClass("active");
		$(this).addClass("active");
	});
	$("#gridview")
	.on(
			"click",
			function() {
				window.location.href="/oasys/workform/nworkform/list.do"; 
			});
</script>

</body>
</html>