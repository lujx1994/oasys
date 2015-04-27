<%@ include file="/WEB-INF/jspf/frontend/head.jsp"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<div id="createform">
		<h2 class="sub-header">填写报表</h2>
		<script src="ckeditor4.4.7/ckeditor.js"></script>
		<form id="cnewworkflow" action="workflow/cworkflow/${form.id}.do"
			method="post" class="form-horizontal" role="form">
			<div class="form-group">
				<div class="col-md-6">
					<input name="workformName" value="${form.name}" type="text"
						class="form-control" placeholder="表单名称">
				</div>
			</div>

			<div class="form-group">
				<div class="col-md-6">
					<select name="mWorkflow" class="form-control">
						<c:forEach items="${defaultFlows}" var="defaultFlow">
							<option value="${defaultFlow.participate}">${defaultFlow.name}</option>
						</c:forEach>
					</select>

				</div>
				<div class="col-md-6">
					<button id="custom" type="button" class="btn btn-info">自定义</button>
				</div>
			</div>

			<div class="form-group">
				<div class="col-md-6">
					<c:choose>
						<c:when test="${param.checked == -1}">
							<h5>
								<div class="alert alert-danger" role="alert">
									<button type="button" class="close" data-dismiss="alert"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<strong>警告!</strong> 添加失败，数据库操作失败，请检查您的填报信息
								</div>
							</h5>
						</c:when>
						<c:when test="${param.checked == 2}">
							<h5>
								<div class="alert alert-warning" role="alert">
									<button type="button" class="close" data-dismiss="alert"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<strong>提醒!</strong> <a href="workform/nworkform/grid.do">您刚才已成功提交过表单，请重新申请报表，再来添加</a>
								</div>
							</h5>
						</c:when>
						<c:when test="${param.checked == 1}">
							<h5>
								<div class="alert alert-success" role="alert">
									<button type="button" class="close" data-dismiss="alert"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<strong>恭喜!</strong> <a href="workform/nworkform/grid.do">提交成功,您可以继续申请表单提交。</a>
								</div>
							</h5>
						</c:when>
						<c:otherwise>
							<h5></h5>
						</c:otherwise>
					</c:choose>
				</div>
			</div>

			<div class="form-group">
				<div class="col-md-8">
					<textarea class="ckeditor" name="editor1">${form.content}</textarea>
				</div>
				<div class="col-md-3 col-md-offset-1">
					<div class="thumbnail">
						<img src="${form.image}" alt="Generic placeholder thumbnail">
						<div class="caption">
							<h3>${form.name}</h3>
							<p>这里可以介绍填写报表要求，和其他相关信息</p>
							<p>
								<a href="workflow/nworkflow/${form.id}.do?checked=0"
									class="btn btn-primary" role="button">重新申请</a>
						</div>
					</div>
				</div>
			</div>

			<div class="form-group">
				<div class="col-md-1">
					<button type="submit" class="btn btn-warning">提交</button>
				</div>
			</div>
		</form>
	</div>


	<div id="createflow">
		<h2 class="sub-header">新建流程</h2>

		<div class="row">
			<div class="col-md-4">

				<div class="divcss" id="roleuser">
					<ul class="list-unstyled">
						<c:forEach items="${roleList}" var="role">
							<li data-roleid="${role.id}">${role.name}</li>
							<li>
								<ul>
									<c:forEach items="${role.userList}" var="user">
										<li data-userid="${user.id}">${user.truename}</li>
									</c:forEach>
								</ul>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<div class="col-md-2">
				<span class="glyphicon glyphicon-minus"></span> <span
					class="glyphicon glyphicon-minus"></span> <span
					class="glyphicon glyphicon-minus"></span> <span
					class="glyphicon glyphicon-minus"></span> <span
					class="glyphicon glyphicon-hand-right"></span>
			</div>

			<div class="col-md-4">
				<div class="divcss" id="addworkflow">
					<ol>

					</ol>

				</div>
			</div>
			<div class="col-md-2">
				<button id="createwl" type="button" class="btn btn-danger">提交</button>
				&nbsp;
				<button id="goback" type="button" class="btn btn-danger">返回</button>
			</div>

		</div>
	</div>


	<hr>

	<footer>
		<p>&copy; Yanoda 2015</p>
	</footer>
</div>
<!-- /container -->

<script>
	$("#createflow").hide();
	$("#custom").on("click", function() {
		$("#createform").hide();
		$("#createflow").show();
	});
	$("#goback").on("click", function() {
		$("#createform").show();
		$("#createflow").hide();
	});

	$("#roleuser > ul > li:odd").hide();
	$("#createwl").attr("disabled", "disabled");

	$("#roleuser > ul > li:even").on("click", function() {
		$("#roleuser > ul > li:odd").hide();
		$(this).next().show();

	});

	$("#roleuser li li").on("click", function() {

		$("#createwl").removeAttr("disabled");
		$("#roleuser li li").removeClass("libgcl");
		$(this).addClass("libgcl");
		$(this).clone().removeClass("libgcl").appendTo("#addworkflow ol");
	});

	$("#addworkflow").on("click", "li", function() {
		$(this).remove();
		if ($("#addworkflow li").length == 0) {
			$("#createwl").attr("disabled", "disabled");
		}
	});

	var wName = "";
	$("#flowName").keyup(function() {
		wName = $(this).val();
	});

	$("#createwl")
			.on(
					"click",
					function() {
						var mWorkflow = "0,";
						$("#addworkflow li").each(function(index) {
							mWorkflow += $(this).data("userid") + ",";
						});
						mWorkflow = mWorkflow
								.substring(0, mWorkflow.length - 1);

						mFlowname = "";
						$("#addworkflow li").each(function() {
							mFlowname = mFlowname + $(this).text() + ",";
						});
						mFlowname = mFlowname
								.substring(0, mFlowname.length - 1);
						mFlowname = "自定义流----[" + mFlowname + "]";

						$("#deleteru").attr("disabled", "disabled");
						$("option").removeAttr("selected");
						var newflow = "<option value=\""+mWorkflow+"\" selected=\"selected\">"
								+ mFlowname + "</option>";
						$("select").append(newflow);
						alert("添加成功");

					});
</script>
<script>
	$(document).ready(function() {
		CKEDITOR.replace('editor1', {
			height : 800,
		});
	});
</script>
</body>
</html>
