<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>    
    
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<button id="btn">at3 test</button>

<p class="name"></p> 

<script type="text/javascript">
$(document).ready(function(){
$("#btn").click(function(){
	$.ajax({
		type:'post',
		url:'at3',
		dataType:'text',
		/* 제이슨 형태로data 이름 ,데이터 ''로 감싸기 */
		/* 여러개 데이터 집어넣기 */
		 data:{
			'id':'m004',
			'name':'lee',
			/* 'age':33 */
			 },
			 success:function(result){
				 console.log(result);
				 
				 //jason데이터를 이용하여 하나의 객체 생성

				 var obj=JSON.parse(result);
				 console.log(obj.id);
				 console.log(obj.name);
				 console.log(obj.age);
                 //p태그에  내용을 
				 $(".name").text(obj.name);




				 
				 },
				 error:function(request,status,error){
					 console.log(error);
					 }

		
		});
	
});	
});

</script>
</body>
</html>