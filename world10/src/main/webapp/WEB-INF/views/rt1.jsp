<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<button>rt1</button>


	<script type="text/javascript">
$(document).ready(function(){
/* 	동적으로 생성된 버튼인 경우 안됨,그럴때는  on */
/* $("button").click(function(){ */
	$("body").on("click","button",function(){
	/*  alert(1111); */
	
	var test1="hello";
	
	
	 $.ajax({
		type:'post',
		url:'/rt1',
		dataType:'text',
		headers:{
			'Content-Type':'application/json','X-HTTP-Method-Override':'POST'
			},
			/* jason갑ㅅ으로 변형stringiry가 */
		data:JSON.stringify({

			/* 변수  값 */
			test1:test1

			}),
			success:function(result){
		
				console.log(result);

				$("p").text(result);
			
				}



		});
	
});



	
});
</script>
</body>
</html>