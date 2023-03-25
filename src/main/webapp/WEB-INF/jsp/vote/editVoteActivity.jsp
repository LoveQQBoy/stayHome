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
	
	var selectText = saveSelectContent();
	
	//選項數選單設置
	var selectNum = $("#selectNum").val();
	$("#optionNumber").val(selectNum)
	
	$("#optionNumber").change(function(){
		selectText = saveSelectContent();
		
		$("#optionList table tbody").html("");
		
		let count = $("#optionNumber").val();
		
		for(let i=1;i<=count;i++){
			let str = "<tr><th scope='row'>" + i +
								"<td><input name='select" + i +
								"' class='form-control' value=" + selectText[i-1] +
								"></td></th></tr>"
			$("#optionList table tbody").append(str);
		}
	});
});

function saveSelectContent(){
	var trCount = $("#optionList table tbody tr").length;
	var text = [];
	for(let i=0;i<5;i++){
		if(i<trCount)
			text[i]=$("#optionList table input").eq(i).val();
		else
			text[i]="";
	}
	
	return text;
}
</script>
</head>
<body>

	<jsp:include page="../layout/sideNavbar.jsp" />
	<div class="container-right">
	<div class="row">
	<!-- ---------------------------程式區-------------------------------- -->
		<h1 class="text-center">發起投票頁面</h1>
		
		<div>
			<form action="${contextRoot}/vote/editVote" method="post">
				<input type="hidden" name="id" value="${voteActivity.id}"/>
				<input type="hidden" name="_method" value="put"/>
				<div>
					<label for="title">標題</label>
					<input name="title" class="form-control" value="${voteActivity.title}"/>
				</div>
				<div>
					<label for="context">內容</label>
					<input name="context" class="form-control" value="${voteActivity.context}"/>
				</div>
				<div>
					<label for="startDate">日期</label>
				</div>
				<div class="input-group">
					<input type="date" name="startDate" class="form-control" value="${voteActivity.startDate}"/>
					<span class="input-group-text">至</span>
					<input type="date" name="endDate" class="form-control" value="${voteActivity.endDate}"/>
				</div>
				<div>
				<label for="optionNumber">選項數</label>
					<input type="hidden" id="selectNum" value="${selectNum}"/>
					<select class="form-select" id="optionNumber">
					  <option value="2">2</option>
					  <option value="3">3</option>
					  <option value="4">4</option>
					  <option value="5">5</option>
					</select>
				</div>
				<div id="optionList">
				<table class="table table-bordered align-middle text-center">
					<tbody>
					<c:if test="${voteActivity.select1!=null}">
				    <tr>
				      <th scope="row">1</th>
				      <td><input name="select1" class="form-control" value="${voteActivity.select1} "/></td>
				    </tr>
					</c:if>
					<c:if test="${voteActivity.select2!=null}">
				    <tr>
				      <th scope="row">2</th>
				      <td><input name="select2" class="form-control" value="${voteActivity.select2} "/></td>
				    </tr>
					</c:if>
					<c:if test="${voteActivity.select3!=null}">
				    <tr>
				      <th scope="row">3</th>
				      <td><input name="select3" class="form-control" value="${voteActivity.select3} "/></td>
				    </tr>
					</c:if>
					<c:if test="${voteActivity.select4!=null}">
				    <tr>
				      <th scope="row">4</th>
				      <td><input name="select4" class="form-control" value="${voteActivity.select4} "/></td>
				    </tr>
					</c:if>
					<c:if test="${voteActivity.select5!=null}">
				    <tr>
				      <th scope="row">5</th>
				      <td><input name="select5" class="form-control" value="${voteActivity.select5} "/></td>
				    </tr>
					</c:if>
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