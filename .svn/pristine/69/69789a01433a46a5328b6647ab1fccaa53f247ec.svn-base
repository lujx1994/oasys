<%@ include file="/WEB-INF/jspf/frontend/head.jsp"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
	<!-- Example row of columns -->
	<div class="row">

		<div class="col-md-12">

			<h2 class="sub-header">待审列表</h2>
			<div class="table-responsive">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>id</th>
							<th>名称</th>
							<th>申请人</th>
							<th>时间</th>
							<th>
							<%-- <%
								String status=request.getParameter("status");
								if(status != null) {
									if(status.equals("success")) {
							%>
										<span class="bg-info">审批成功</span>
							<%
									}else {
							%>
										<span class="bg-info">审批失败</span>
							<%			
									}
								}else {
									out.print("#");
								}
							%> --%></th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${rows == null}">
						<tr>
							<td class="text-center" colspan="5">没有相关记录</td>
						</tr>
						</c:if>
						<c:if test="${rows != null}">
							<c:forEach items="${rows}" var="row">
						<tr>
							<td>${row.id}</td>
							<td>${row.name}</td>
							<td>${row.create_user_id}</td>
							<td>${row.create_time}</td>
							<td><a href="workflow/detailflow/${row.id}.do"><span class="glyphicon glyphicon-pencil">.....</span></a></td>
						</tr>
							</c:forEach>
						</c:if>

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

	<hr>

	<footer>
		<p>&copy; Yanoda 2015</p>
	</footer>
</div>
<!-- /container -->

</body>
</html>