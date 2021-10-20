<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<style type="text/css">
.tit { 
	display:block;
	width:150px;
}
.valid-msg {
	color:red;
	font-size:0.5vw;
}

</style>
</head>
<body>
	<h1>로그인</h1>
	<hr>
	<form action="/member/login" method="post">
	
		<c:if test="${not empty param.err}">
			<span class="valid-msg">아이디나 비밀번호를 확인하세요.</span>
		</c:if>
		
		<span class='tit'>ID : </span>
		<input type="text" name="userId">
		<span class='tit'>Password : </span>
		<input type="password" name="password">
		<button>로그인</button>
	</form>
	
	
</body>
</html>