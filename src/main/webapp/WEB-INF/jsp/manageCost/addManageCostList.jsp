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
</head>
<body>
	<jsp:include page="../layout/sideNavbar.jsp" />
	<div class="container-right">
		<div class="row justify-content-center">
			<!-- ---------------------------程式區-------------------------------- -->
			<h1>管理費</h1>
			
			<div class="col-6">
				<form method="post" action="${contextRoot}/manageCost/addManageCostList">
					<div class="mb-3">
						<label for="manageTitle">期別名稱</label>
						<input type="text" id="manageTitle" name="manageTitle"/>
					</div>
					
					<div class="mb-3">
						<select name="dateYear" id="dateYear"></select>
						<label for="dateYear">年</label>
						<script type="text/javascript">
							var thisYear = new Date().getFullYear();
							var str = "<option value="+ thisYear +" selected>"+thisYear+"</option>";
							for(let i=0;i<4;i++){
								thisYear += 1;
								str += "<option value="+ thisYear + ">"+thisYear+"</option>";
							}
							$("#dateYear").html(str);
						</script>
						<select name="dateMonth" id="dateMonth"></select>
						<label for="dateMonth">月</label>
						<script type="text/javascript">
							var thisMonth = (new Date().getMonth()) + 1;
							console.log(thisMonth)
							var monthValue = 1;
							var str = "";
							for(let i=0;i<12;i++){
								str += "<option value="+ monthValue + ">"+monthValue+"</option>";
								monthValue += 1;
							}
							$("#dateMonth").html(str);
							$("#dateMonth option[value="+ thisMonth +"]").attr('selected',true)
						</script>
					</div>
					
					<div>
						<label for="squareF">每坪費用</label>
						<input type="number" id="squareF" name="squareF" />
					</div>
					
					<div>
						<label for="parkF">車位費用</label>
						<input type="number" id="parkF" name="parkF" />
					</div>
					
					<div>
						<button type="submit" class="btn btn-primary">送出</button>
						<button type="reset" class="btn btn-primary">重設</button>
					</div>
				</form>
			</div>
			<!-- ---------------------------程式區-------------------------------- -->
		</div>
	</div>
</body>
</html>