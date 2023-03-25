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
<script>
$(function() {
	
	//當使用者上傳一個檔案後將進入Web Server回應的新頁面。
    //又當使用者「返回前頁」時，需要「重新預覽」前回點選擬上傳的圖片。
    //$("#reportPhoto")是jquery物件(陣列)，$("#reportPhoto")[0]是DOM元素
	previewImg($("#reportPhoto")[0].files);	
	
     //當選檔變更時，立即預覽之前被選擇的照片
     $("#reportPhoto").change(function () {
     	//$("#img-preview").attr("src","").css('display','block');
     	$("#img-preview").attr("src","")
         previewImg(this.files);
     });
     
     function previewImg(files) {
         if (files.length == 0){
        	 $("#img-preview").css('display','none');
        	 return;
         }else{
        	 $("#img-preview").css('display','block');
         }
            
         var file = files[0];
               	                      
         var fr = new FileReader();
         //註冊當選檔被讀取完成後之事件處理器
         fr.onload =
             function () {
                 $("#img-preview").attr({ src: fr.result});
                 /*  fr.result: The file's contents. 內容如下:
                     data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAA ...
                 */
             };
         fr.readAsDataURL(file);
     }
     	
	
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
	
	
})

</script>	
</head>

<body>
	<jsp:include page="../layout/navbar.jsp" />
	<!-- ======= Breadcrumbs ======= -->
	<div class="breadcrumbs mb-5">
		<div class="container">
			<h2>通報維修</h2>
			<p>有看到任何器材損壞了嗎?都可以來這邊回報喔!</p>
		</div>
	</div>
	<!-- End Breadcrumbs -->
	<div class="container">
		<!-- ---------------------------程式區-------------------------------- -->
		
		<form:form modelAttribute="repairForReport" enctype="multipart/form-data" id="form">				
			<label for="reportText">留言(限百字內):</label>				
			<form:textarea path="description" class="form-control" id="reportText" rows="" cols="" required="required"/>						
			<div class="text-danger">${notSpace}</div>
			<br/>
			
			<label for="reportPhoto">照片:</label>	
			<br/>	
            <form:input path="m_repairPhoto" type="file" id="reportPhoto" />
            <br/><img src="" id="img-preview" class="h-50 w-50" /> 
            				
			<br/><br/>
			<button class="btn btn-primary" type="submit">送出</button>	
			<button class="btn btn-primary" type="button" onclick="location.href='${contextRoot}/repair/residentList'">取消</button>										
		</form:form>
				
		<!-- ---------------------------程式區-------------------------------- -->
	</div>


</body>
</html>