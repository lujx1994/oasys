<%@ include file="/WEB-INF/jspf/frontend/head.jsp"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<!-- Example row of columns -->
	<div class="page-header">

		<div class="pull-left">
			<h2>
				申请报表<small><span class="label label-info">列表视图</span></small>
			</h2>
		</div>
		<div class="pull-right">
			<h2 id="gridview">
				<span class="glyphicon glyphicon-hand-right" aria-hidden="true"></span>
				<small><span class="label label-success">网格视图</span></small>
			</h2>
		</div>
		<div class="clearfix"></div>
	</div>
	
	
	<div class="row">
		<div class="col-md-3">

			<div class="list-group">
			<c:forEach items="${formTypeList}" var="formType">
				<a href="javascript:void(0)" data-typeid="${formType.id}" class="list-group-item">${formType.name}</a>
			</c:forEach>
			</div>


		</div>
		<div class="col-md-9">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>编号</th>
						<th>报表名称</th>
						<th>申请操作</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${formTypeList}" var="formType">
					<c:forEach items="${formType.formList}" var="form">
					<tr data-typeid="${formType.id}">
						<td>${form.id}</td>
						<td>${form.name}</td>
						<td>
						<a href="workflow/nworkflow/${form.id}.do?checked=0">
						<span class="glyphicon glyphicon-pencil">.....</span>
						</a>
						</td>
					</tr>
					</c:forEach>
				</c:forEach>
				</tbody>
			</table>
		</div>

	</div>

	<hr>

	<footer>
		<p>&copy; Yanoda 2015</p>
	</footer>
</div>
<!-- /container -->

<script>
	$(".list-group a:first-child").addClass("active");
	var typeid = $(".list-group a:first-child").data("typeid");
	$("tbody tr").hide();
	$("tr[data-typeid=\""+typeid+"\"]").show();
	$(".list-group a").on("click", function() {
		var type_id=$(this).data("typeid");
		$("tbody tr").hide();
		$("tr[data-typeid=\""+type_id+"\"]").show();
		$(this).siblings().removeClass("active");
		$(this).addClass("active");
	});
	
	$("#gridview")
	.on(
			"click",
			function() {
				window.location.href="/oasys/workform/nworkform/grid.do"; 
			});
</script>

</body>
</html>