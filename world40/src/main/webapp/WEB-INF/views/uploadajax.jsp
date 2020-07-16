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

<style type="text/css">

.fileDrop{
width=80%;
height:200px;
border:1px dotted red;
}

</style>


</head>
<body>


<div class="fileDrop"></div>
<div class="uploadedList"></div>

<!-- head단에 놓을때 $(document )꼭필요 -->






<script type="text/javascript">




$(document).ready(function(){



	$(".uploadedList").on("click",".deletefile",function(event){
	event.preventDefault();
	var that=$(this);


	$.ajax({
   type:'post',
   url:'/deletefile',
   dataType:'text',
   data:{
	   
		filename:$(this).attr("href")
		
	   },
	   success:function(result){
       alert(result);
		   that.parent("div").remove();
		   }

		});
	
	});

	
	/*  정적 생성 여러개 이벤트*/
	$(".fileDrop").on("dragenter dragover",function(event){
		event.preventDefault(); /* 이벤트 막아줌*/
	});
	$(".fileDrop").on("drop",function(event){
		event.preventDefault();	



		var files=event.originalEvent.dataTransfer.files;
	    /*  한개 파일만 업로드*/
		var file=files[0];
        /* form 태그 만듦 */
		var formData=new FormData();
		formData.append("file",file);


		$.ajax({
					type:'post',
					url:'uploadajax',  /* 받은 데이터를 uploadajaxdptj 처리 post방식으로  */
					dataType:'text',  /*  받을때 타입은 text으로 받음 즉 result는 text*/
					data:formData, /* 보내는건 formData타입으로 보내고  */
					/* data가 바이너리 형태로 묶여져 전달 */
					processData:false,
					contentType:false,
					success:function(result){
							console.log(result);
							/* <a>클릭하면 1)이미지 하일:원본파일을 보게 해주고 2)비 이미지파일 :다운로드 */
							/* displayfile?filename="+getImageLink(result)+" */
							
							var str="<div><a href='/displayfile?filename="+getImageLink(result)+"'>";/*/가 있으면 전대 좌표를 의미./가 없고 board폴더에 있었으면 /board/displayfile...로 위치 지정 */

							if(checkImage(result)){
								str += "<img src='/displayfile?filename="+result+"'/>"
								}else{
									str += "<img src='/resources/show.png'/>"
									}


						
							
							str +=getOriginalName(result);
							str +="</a><a class='deletefile' href='"+result+"'><span class='glyphicon glyphicon-trash'></span></a> </div>";

							$(".uploadedList").append(str);
							

						}

				});			
		
		});	
});


       function getImageLink(result){
    	   if(checkImage(result)){
        	   return result.substring(0,12)+result.substring(14);

        	   }else{
            	   return result;
            	   }
    	   
           }





			function getOriginalName(filename){
				if(checkImage(filename)){
					var idx=filename.indexOf("_");
					idx=filename.indexOf("_",idx+1);
					
				    return filename.substring(idx+1);
					
					}else{
						var idx=filename.indexOf("_");
					    return filename.substring(idx+1);
						
						}
				

				}

			function checkImage(filename){
					var idx = filename.lastIndexOf(".");
					var format = filename.substring(idx+1).toUpperCase();
					if(format=='PNG'||format=='JPG'||format=='JPEG'||format=='GIF'){
							return true;
					}else{
							return false;
						}
					
				}
</script>

</body>
</html> 






















