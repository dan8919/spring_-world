<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.co.domain.MemberDTO"%>
<%@page import="java.util.List"%>
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
<button>at4 test</button>
<p></p>

<%
List<MemberDTO> list=new ArrayList<MemberDTO>();
list.add(new MemberDTO("m001","kim",22));
list.add(new MemberDTO("m002","lee",25));
list.add(new MemberDTO("m003","park",32));

ObjectMapper mapper=new ObjectMapper();
String listStr=mapper.writeValueAsString(list);
pageContext.setAttribute("listStr", listStr);
%>

<!-- 버튼 누르면 위의 데이터 컨트롤러로 넘어가게 -->

<script type="text/javascript">
$(document).ready(function(){
$("button").click(function(){
	$.ajax({
		type:'post',
		url:'at4',
	/* 	데이터를 받은후 의 데이터 타입 */
		dataType:'text',
		
		 data:{
			listStr:JSON.stringify(${listStr})
			 },
			 success:function(result){
				 console.log(result);
/* parse형탱로 데이터 사용 */
				 var obj=JSON.parse(result);

				/* alert(obj[0]['id']); */
             $("p").text(obj[0]['id']);

             for(var i=0;i<obj.length;i++){
/* x 멤버변수 */
                 for(x in obj[i]){
                     console.log(x);
                     console.log(obj[i][x]);
                     console.log(":::::::::::::::::::::::::::::::::")

                     }

                 }


				 
				 
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