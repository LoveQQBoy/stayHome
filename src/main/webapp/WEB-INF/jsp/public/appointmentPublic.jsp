<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript"
	src="${contextRoot}/js/jquery-3.6.3.min.js"></script>
<script type="text/javascript"
	src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="https://unpkg.com/axios@1.1.2/dist/axios.min.js"></script>
<script>
$(function(){
		
	$("#form").on("submit", function(e) {
		e.preventDefault();
		swal({
			  title: "您確定送出?",				 
			  icon: "warning",
			  buttons: true,
			})
			.then((willDelete) => {
			  if (willDelete) {			   
			    this.submit();
			  } 
			});
	});
			
	
	$("#publicName").on("change",function(){		
		$("#appointmentTime").empty();
			
		$("img").attr("src",'${contextRoot}/publicPicture/' + $("#publicName").val())
							
 		let queryString = "?publicId=" + $("#publicName").val()

		axios.get('${contextRoot}/public/appointmentLimit' + queryString)
		.then(res => {
		    $("#limitDate").html("請預約"+res.data.dateLimit+"前，禮拜"+res.data.daysOfWeek+"開放");
		    insertTimes(res.data.times);
		    
		    let queryString1 = "?publicId=" + $("#publicName").val() + "&appointmentTime=" + $("#appointmentTime").val()
		    return axios.get('${contextRoot}/public/okAppointmentNumber' + queryString1);
		})
		.then(res => {
// 		    console.log(res);
// 		    console.log(res.data);
		    $("#limitNumber").html("可預約人數:"+res.data);
		})
		.catch(err => {
		    console.error(err); 
		});			
	})
	
	
	function insertTimes(data){	    
	     let str = ''
	     for(i=0; i < data.length ;i++){
	    	str += 
	      	"<option value=" + data[i] + ">" + data[i] + "</option>"
// 	      	$('#appointmentTime').append($('<option></option>').attr('value', data[i]).text(data[i]));
	     }		
	     $("#appointmentTime").append(str);	     	     
	}
		
	
	if(${publicAppointment.id!=null}){
		$("#publicName").val('${publicAppointment.public_.id}');
		
		let queryString = "?publicId=" + $("#publicName").val()

		axios.get('${contextRoot}/public/appointmentLimit' + queryString)
		.then(res => {
		    $("#limitDate").html("請預約"+res.data.dateLimit+"前，禮拜"+res.data.daysOfWeek+"開放");
		    insertTimes(res.data.times);
		    		    
		    //////axios非同步和上面一樣，只多加下面
		    if(!$("[value='${publicAppointment.appointmentTime}']").length){
		    	let str='<option value="${publicAppointment.appointmentTime}" selected>${publicAppointment.appointmentTime}</option>';
		 		$("#appointmentTime").append(str);
		    }
		    
		    $("#appointmentTime").val('${publicAppointment.appointmentTime}')
		    ////////
		    
		    let queryString1 = "?publicId=" + $("#publicName").val() + "&appointmentTime=" + $("#appointmentTime").val()
		    return axios.get('${contextRoot}/public/okAppointmentNumber' + queryString1);
		})
		.then(res => {
		    $("#limitNumber").html("可預約人數:"+res.data);
		})
		.catch(err => {
		    console.error(err); 
		});			
		
		$("img").attr("src",'${contextRoot}/publicPicture/${publicAppointment.public_.id}')
	}
	
	
	
})

</script>

</head>
<body>
	<jsp:include page="../layout/navbar.jsp" />
	<!-- ======= Breadcrumbs ======= -->
	<div class="breadcrumbs mb-5">
		<div class="container">
			<h2>用戶預約公設</h2>
			<p></p>
		</div>
	</div>
	<!-- End Breadcrumbs -->
	<div class="container">
		<!-- ---------------------------程式區-------------------------------- -->

		<form:form modelAttribute="publicAppointment"
			action="${contextRoot}/public/appointmentPublic" id="form">
			<img height='350px' src='' />
			<br />

			<form:input path="id" type="hidden" />
			<form:input path="date" type="hidden" />

			<label for="publicName">公設名稱:</label>
			<form:select path="publicId" id="publicName" required="required">
				<form:option value="">請選擇</form:option>
				<form:options items="${publicsByState1}" />
			</form:select>
			<br />

			<label for="appointmentDate">預約日期:</label>
			<form:input type="date" path="appointmentDate" id="appointmentDate"
				required="required" />
			<span id=limitDate class="text-danger"></span>
			<br />

			<label for="appointmentTime">時段:</label>
			<select name="appointmentTime" id="appointmentTime"
				required="required">
				<option value="">請選擇</option>
			</select>
			<br />

			<!-- 			因為不能用js增加option，所以註解掉;不重刷頁面          -->
			<!-- 			<label for="appointmentTime">時段:</label> -->
			<%-- 			<form:select path="appointmentTime" id="appointmentTime" required="required"> --%>
			<%-- 				<form:option value="">請選擇</form:option> --%>
			<%-- 				<form:options items="${publicTimes}" /> --%>
			<%-- 			</form:select> --%>
			<!-- 			<br /> -->

			<label for="appointmentNumber">人數:</label>
			<form:input path="appointmentNumber" id="appointmentNumber"
				required="required" />
			<span id=limitNumber class="text-danger"></span>
			<br />

			<br />
			<br />
			<button class="btn btn-primary" type="submit">送出</button>
			<c:if test="${publicAppointment.id!=null}">
				<button class="btn btn-danger" type="button"
					onclick="location.href='${contextRoot}/public/appointmentCancel/${publicAppointment.id}'">取消預約</button>
			</c:if>
			<button class="btn btn-secondary" type="button"
				onclick="location.href='${contextRoot}/public/appointmentList?p=${pageNumberS}&date=${date}&apState=${apState}&publicName=${publicName}'">取消</button>
		</form:form>

		<!-- ---------------------------程式區-------------------------------- -->
	</div>


</body>
</html>