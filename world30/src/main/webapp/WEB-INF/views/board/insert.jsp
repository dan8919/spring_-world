  
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
<script src="/resources/js/uploadfn.js" type="text/javascript"></script>
<title>Insert title here</title>
<style type="text/css">
	.fileDrop{
		width : 80%;
		height : 200px;
		border : 1px solid red;
		margin : auto;
	}
	
	.uploadedList{
		margin-top: 50px;
	}
	.uploadedList li{
		list-style : none;
	}
	.originalfilename{
		overflow : hidden;
		white-space : nowrap;
		text-overflow : ellipsis; 
	}
</style>



</head>
<body>
	<div class="container-fluid">
		<div class="row">
			bootstrap v3 적용<br>
		</div>
	</div>
	<div class="container">
		<div class="row">
		<h1>글쓰기</h1>
			<form action="/board/insert" method="post">
				<div class="form-group">
				<label for="title">제목</label>
				<input name="title" required id="title" class="form-control">
				</div>
				<div class="form-group"> 
				<label for="writer">작성자</label> 
				<input name="writer" required id="writer" class="form-control"> 
				</div>
				<div class="form-group">
				<label for="content">내용</label>
				<textarea name="content" required rows='5' id="content" class="form-control"> </textarea>
				</div>
			</form>
			
			<div class="form-group">
				<label for="content">업로드할 파일을 드랍시키세요.</label>
				<div class="fileDrop"></div>
				<ul class="uploadedList clearfix">
					<%-- 
					<li class="col-xs-4">
						<a href="#">
							<img src="/resources/show.png">
						</a>
						<p class="originalfilename">
							<a><span class='glyphicon glyphicon-trash'></span></a>
							aadddddddddddddddadsfasdfadfasdfddddsafsfasdffffffffffffffdddddddddddddddddda.txt
						</p>
					</li>
					--%>
				</ul>
			</div>
			
			
			<div class="form-group">
				<button class="btn btn-danger" id="insertbtn">등록</button>
				<button class="btn btn-link" id="listbtn">목록</button>
			</div>
		</div>


	</div>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#insertbtn").click(function(){
				$("form").submit();
				});
			$("#listbtn").click(function(){
				location.href="/board/list";
				});
		
			$(".fileDrop").on("dragenter dragover", function(event){
				event.preventDefault();
			});
			$(".fileDrop").on("drop", function(event){
				event.preventDefault();
				
				var files = event.originalEvent.dataTransfer.files;
				var file = files[0];			
				var formData = new FormData();
				formData.append("file",file);
				$.ajax({
					type : 'post',
					url : '/uploadajax',
					dataType : 'text',
					data : formData,
					processData : false,
					contentType : false,					
					success : function(result){						
						var str = "<li class='col-xs-4'><a href ='/displayfile?filename="+getImageLink(result)+"'>";
						if(checkImage(result)){
							str += "<img src = '/displayfile?filename="+result+"' />";
						} else{
							str += "<img src = '/resources/show.png'/>";
						}
						
						str += "</a><p class='originalfilename'><a class='deletefile' href='"+result+"'><span class='glyphicon glyphicon-trash'></span></a>";
						str += getOriginalName(result)+"</p></li>";
						$(".uploadedList").append(str);
					}
				});
			});			
		});
	</script>


</body>
</html>