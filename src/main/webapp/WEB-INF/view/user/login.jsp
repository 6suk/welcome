<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<%@ include file="../common/heading.jsp"%>
<title>사용자 로그인</title>
</head>
<body>
	<%@ include file="../common/top.jsp"%>
	<div class="inputtb container justify-content-center">
		<div class="inputtb content">
			<div class="inputtb content-title pb-4">
				<h3>로그인</h3>
				<div class="">
					<button class="btn small subcolor"
						onclick="location.href='/board/list'">Home</button>
				</div>
			</div>

			<form action="/user/login" class="pt-3" method="post">
				<table class="inputtb content-desc">
					<tr>
						<th>아이디</th>
						<td><input type="text" name="uid" placeholder="아이디"
							maxlength="20" required /></td>
					</tr>
					<tr>
						<th>패스워드</th>
						<td><input type="password" name="pwd" placeholder="패스워드"
							maxlength="60" required /></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit"
							class="btn full maincolor" value="로그인" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
    <%@ include file="../common/bottom.jsp"%>
  </body>
</html>