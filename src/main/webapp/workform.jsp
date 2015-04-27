<%@ include file="/WEB-INF/jspf/frontend/head.jsp"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<!-- Example row of columns -->
	<div class="row">
  <div class="col-sm-6 col-md-4">
    <div class="thumbnail">
      <img src="..." alt="...">
      <div class="caption">
        <h3>Thumbnail label</h3>
        <p>...</p>
        <p><a href="#" class="btn btn-primary" role="button">Button</a> <a href="#" class="btn btn-default" role="button">Button</a></p>
      </div>
    </div>
  </div>
</div>

	<hr>

	<footer>
		<p>&copy; Yanoda 2015</p>
	</footer>
</div>
<!-- /container -->

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->

<script src="../bootstrap/js/jquery-1.11.1.js"></script>

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
</script>


<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../bootstrap/js/bootstrap.min.js"></script>

</body>
</html>