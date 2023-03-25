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
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.1.0/chart.min.js"></script>
<style type="text/css">
.chartTable{
width: 420px;
height: 200px;
}
</style>
</head>
<body>
<jsp:include page="../layout/navbar.jsp" />
<!-- ======= Breadcrumbs ======= -->
         	 <div class="breadcrumbs mb-5">
            	<div class="container">
              	<h2>投票結果頁面</h2>
              	<p>這次的投票結果出爐了
              	</p>
           		</div>
          	 </div><!-- End Breadcrumbs -->
	<div class="container">
	<!-- ---------------------------程式區-------------------------------- -->
		<h1 class="text-center">投票結果頁面</h1>
		<div class="border border-dark">
			<input type="hidden" name="voteid" value="${voteActivity.id}" />
				
				<div>
					<h4>標題</h4>
					<p>${voteActivity.title}</p>
				</div>
				<div>
					<h4>內容</h4>
					<p>${voteActivity.context}</p>
				</div>
				<div class="chartTable border border-dark">
					<canvas id="myChart" width="200" height="80"></canvas>
					<script type="text/javascript">
						//選項標籤及數值資料處理
						var lablesData = ['${voteActivity.select1}',
															'${voteActivity.select2}',
															'${voteActivity.select3}',
															'${voteActivity.select4}',
															'${voteActivity.select5}'];
						var selectData = [${voteCount[1]},
															${voteCount[2]},
															${voteCount[3]},
															${voteCount[4]},
															${voteCount[5]}]
						var count=0;
						lablesData.forEach(function(value){
							if(value==""){
								count++;								
							}
						});
						for(let i=0;i<count;i++){
							lablesData.pop();
							selectData.pop();
						}
						//console.log(lablesData)
						//console.log(selectData)
						
						var ctx = document.getElementById('myChart');
						var chart = new Chart(ctx, {
							  type: "bar", // 圖表類型
							  data: {
							    labels: lablesData, //顯示區間名稱
							    datasets: [{
							        label: "標籤名稱", // tootip 出現的名稱
							        data: selectData, // 資料
							        backgroundColor: "rgba(75, 192, 192, 0.2)", // bar 顏色
							        borderColor: "rgba(75, 192, 192, 1)", // bar 外框線顏色
							        borderWidth: 1 // 外框線寬度
							        //barThickness: "20"
							      }]
							  },
								options: {
							    indexAxis: 'y',
							    scales: {
						        x: {
						          max: ${voteCount[0]},
						          ticks: {
					            	stepSize: 1
					            }
						        }
							    },
							    plugins: {
		                legend: {
	                    display: false // This hides all text in the legend and also the labels.
		                }
			            }
							  }
							});
					</script>
				</div>
				
				<a href="${contextRoot}/vote/voteFrontPage" class="btn btn-primary">回投票列表</a>
		</div>
	<!-- ---------------------------程式區-------------------------------- -->
	</div>
</body>
</html>