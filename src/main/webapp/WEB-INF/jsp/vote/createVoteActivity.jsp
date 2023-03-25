<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="../js/jquery-3.6.3.min.js"></script>
<script type="text/javascript">
$(function(){
	var initSelect = $("#optionList table tbody").html();
	$("#optionNumber").change(function(){
		$("#optionList table tbody").html(initSelect);
		
		let count = $("#optionNumber").val();
		
		for(let i=3;i<=count;i++){
			let str = "<tr><th scope='row'>"+i+
								"<td><input name='select"+i+"' class='form-control'/></td></th></tr>"
			$("#optionList table tbody").append(str);
		}
	});
});
</script>
</head>
<body>

	<jsp:include page="../layout/sideNavbar.jsp" />
	<div class="container-right">
	<div class="row">
	<!-- ---------------------------程式區-------------------------------- -->
		<h1 class="text-center">發起投票頁面</h1>
		
		<div>
			<form action="${contextRoot}/vote/createVote" method="post">
				<div>
					<label for="title">標題</label>
					<input name="title" class="form-control"/>
				</div>
				<div>
					<label for="context">內容</label>
					<input name="context" class="form-control"/>
				</div>
				<div>
					<label for="startDate">日期</label>
				</div>
				<div class="input-group">
					<input type="date" name="startDate" class="form-control" />
					<span class="input-group-text">至</span>
					<input type="date" name="endDate" class="form-control" />
				</div>
				<div>
				<label for="optionNumber">選項數</label>
					<select class="form-select" id="optionNumber">
					  <option value="2" selected>2</option>
					  <option value="3">3</option>
					  <option value="4">4</option>
					  <option value="5">5</option>
					</select>
				</div>
				<div id="optionList">
				<table class="table table-bordered align-middle text-center">
					<tbody>
				    <tr>
				      <th scope="row">1</th>
				      <td><input name="select1" class="form-control"/></td>
				    </tr>
				    <tr>
				      <th scope="row">2</th>
				      <td><input name="select2" class="form-control"/></td>
				    </tr>
					</tbody>
				</table>
				</div>
				<div>
					<button type="submit">送出</button>
				</div>
			</form>
		</div>
		
	<!-- ---------------------------程式區-------------------------------- -->
	</div>
	</div>

</body>
</html>