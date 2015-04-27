<%@ include file="/WEB-INF/jspf/frontend/head.jsp"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.ArrayList"%>
<%
	String accountInfo = (String) request.getAttribute("userInfo");
	ArrayList<String> finishInfo = (ArrayList<String>) request
			.getAttribute("finishInfo");
%>
<div class="container">
	<h2 class="sub-header">审批报表</h2>
	<script src="ckeditor/ckeditor.js"></script>

	<button type="button" class="btn btn-default">
		发起人：<%=accountInfo%></button>
	<button type="button" class="btn btn-primary">已签人员：</button>
	<%
		if (finishInfo == null) {
	%>
	<button type="button" class="btn btn-success">无</button>
	<%
		} else {
			int count = 1;
			for (String info : finishInfo) {
				if (count % 5 == 0) {
	%>
	<button type="button" class="btn btn-success">More..</button>
	<%
		break;
				}
	%>
	<button type="button" class="btn btn-success"><%=info%></button>
	<%
		count++;
			}
		}
	%>
	<hr>

	<form action="workflow/modifyflow/${flowid}.do" method="post" class="form" role="form">

		<div class="row">
			<div class="col-md-8">
				<textarea class="ckeditor" name="editor1">
				<%=request.getAttribute("content")%>
      			</textarea>
			</div>

			<div class="col-md-1">
				<div class="radio">
					<label> <input type="radio" name="decision"
						id="optionsRadios1" value="agree" checked> 同意
					</label>
				</div>

				<div class="radio">
					<label> <input type="radio" name="decision"
						id="optionsRadios2" value="reject"> 拒绝
					</label>
				</div>

				<hr>
				<input name="flowid" value="<%=request.getAttribute("flowid")%>"
					type="hidden">
				<button type="submit" class="btn btn-warning">提交</button>
			</div>

			<div class="col-md-3">
				<div class="thumbnail">
					<img src="images/untitled.png" alt="Generic placeholder thumbnail">
					<div class="caption">
						<h3>其他需审批报表</h3>
						<p>Cras justo odio, dapibus ac facilisis in, egestas eget
							quam. Donec id elit non mi porta gravida at eget metus. Nullam id
							dolor id nibh ultricies vehicula ut id elit.</p>
						<p>
							<a href="#" class="btn btn-primary" role="button">申请</a>
					</div>
				</div>
			</div>
		</div>
	</form>

	<hr>

	<footer>
		<p>&copy; Yanoda 2015</p>
	</footer>
</div>
<!-- /container -->

</body>
</html>
