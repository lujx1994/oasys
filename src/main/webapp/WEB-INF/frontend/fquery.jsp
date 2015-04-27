<%@ include file="/WEB-INF/jspf/frontend/head.jsp"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
	<!-- Example row of columns -->

	<h2 class="sub-header">按月查询</h2>

	<form action=">query/main.do" method="post" class="form-horizontal" role="form">
		<div class="form-group">
			<label for="querytype" class="col-md-2 control-label">类型</label>
			<div class="col-md-4" id="querytype">
				<select name="mytype" class="form-control">
					<option value="myapplication">申请</option>
					<option value="finishedflow">审批</option>
				</select>
			</div>
		</div>
	
		<div class="form-group">
			<div class="col-md-2"></div>
			<div class="col-md-4">
				<select name="year" class="form-control">
					<option value="2015">2015年</option>
					<option value="2014">2014年</option>
					<option value="2013">2013年</option>
				</select>
			</div>
			<div class="col-md-6"></div>
		</div>

		<div class="form-group">
			<div class="col-md-2"></div>
			<div class="col-md-4">
				<select name="month" class="form-control">
					<option value="1">01月</option>
					<option value="2">02月</option>
					<option value="3">03月</option>
					<option value="4">04月</option>
					<option value="5">05月</option>
					<option value="6">06月</option>
					<option value="7">07月</option>
					<option value="8">08月</option>
					<option value="9">09月</option>
					<option value="10">10月</option>
					<option value="11">11月</option>
					<option value="12">12月</option>
				</select>
			</div>
			<div class="col-md-6"></div>
		</div>
		
		<div class="form-group">
		<div class="col-md-2"></div>
		<div class="col-md-4">
			<button type="submit" style="width:180px;" class="btn btn-warning">提交</button>
		</div>
		<div class="col-md-6"></div>
		</div>

	</form>

<%-- <c:if test=${param.checked == ""}> --%>
	<div class="row">
		<div class="col-md-12">
			<h2 class="sub-header">查询结果</h2>
			<div class="table-responsive">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>id</th>
							<th>名称</th>
							<th>申请人</th>
							<th>时间</th>
							<th>状态</th>
							<th>#</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${rows == null}">
						<tr>
							<td class="text-center" colspan="6">没有相关记录</td>
						</tr>
						</c:if>
						<c:forEach items="${rows}" var="row">
							<tr class=<c:if test="${row.status == 2}">"danger"</c:if>>
								<td>${row.id}</td>
								<td>${row.name}</td>
								<td>${row.create_user_id}</td>
								<%-- <td><%=rbac.get(Integer.valueOf(row.get(2).toString())).getFullname() %></td> --%>
								<td>${row.create_time}</td>
								<td>
								<c:if test="${row.status==0}">已审批</c:if>
								<c:if test="${row.status==1}">未完成</c:if>
								<c:if test="${row.status==2}">拒绝</c:if>
								</td>
								<td><a href="detailmyapplication?flowid=${row.id}"><span class="glyphicon glyphicon-pencil"></span></a>&nbsp;
								<a data-uname="${row.id}" href="#"><span class="glyphicon glyphicon-remove"></span></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<c:if test="${rows != null}">
			<div class="text-center">
				<ul class="pagination">
					<c:if test="${page.nowPage > 1}">
						<li><a href="rmyapplication?pageNumber=${page.nowPage - 1}">&laquo;</a></li>
					</c:if>
					<c:if test="${page.nowPage <= 1}">
						<li class="disabled"><a href="#">&laquo;</a></li>
					</c:if>
					<c:if test="${page.nowPage <= 5}">
						<c:forEach var="i" begin="1" end="${page.countPage}" step="1">
       							<li class=<c:if test="${page.nowPage == i}">"active"</c:if>>
								<a href="rmyapplication?pageNumber=${i}">${i}</a>
								</li>
     					</c:forEach>
					</c:if>
					<%-- <c:if test="${page.nowPage + 4 >= page.countPage}">
						<c:forEach var="i" begin="${page.countPage - 4}" end="${page.countPage}" step="1">
       							<li class=<c:if test="${page.nowPage == i}">"active"</c:if>>
								<a href="rmyapplication?pageNumber=${i}">${i}</a>
								</li>
     					</c:forEach>
					</c:if>
					<c:if test="${page.nowPage + 4 < page.countPage && page.nowPage > 5}">
						<c:forEach var="i" begin="${page.nowPage}" end="${page.nowPage + 4}" step="1">
       							<li class=<c:if test="${page.nowPage == i}">"active"</c:if>>
								<a href="rmyapplication?pageNumber=${i}">${i}</a>
								</li>
     					</c:forEach>
					</c:if> --%>
					<c:if test="${page.nowPage < page.countPage}">
						<li><a href="rmyapplication?pageNumber=${page.nowPage + 1}">&raquo;</a></li>
					</c:if>
					<c:if test="${page.nowPage >= page.countPage}">
						<li class="disabled"><a href="#">&raquo;</a></li>
					</c:if>
				</ul>
			</div>
			</c:if>

		</div>
	</div>
<%-- </c:if> --%>
<%@ include file="/WEB-INF/jspf/frontend/footer.jsp"%>