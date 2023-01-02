<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/heading.jsp"%>
<meta charset="UTF-8" />
<title>정보 수정</title>

</head>
<body>
	<%@ include file="../common/top.jsp"%>
	<div class="inputtb container justify-content-center">
		<div class="inputtb content">
			<div class="inputtb content-title py-4 mt-5">
				<h3>${loginuser.uid eq 'admin' ? '회원 삭제' : '회원 탈퇴' }</h3>
				<div class="">
				<c:set var="url" value="/user/update/${user.uid}"></c:set>
				<c:if test="${loginuser.uid eq 'admin' }">
				<c:set var="url" value="/user/list"></c:set>
				</c:if>
					<button class="btn small subcolor"
						onclick="location.href='${url}'">돌아가기</button>
				</div>
			</div>
			<form action="/user/delete" class="pt-3" method="post">
				<table class="inputtb content-desc">
					<tr>
						<th>아이디</th>
						<td><input type="hidden" name="uid" value="${user.uid}" /> <input
							type="text" name="uid" placeholder="*아이디" value="${user.uid}" disabled /></td>
					</tr>
					<tr>
						<th>이름</th>
						<td><input type="text" name="name" placeholder="*이름"
							maxlength="10" required value="${user.uname}" disabled/></td>
					</tr>
					<tr>
						<th>이메일</th>
						<td><c:choose>
								<c:when test="${empty user.email}">
									<input type="email" name="email" placeholder="이메일"
										maxlength="20" value="${null}" disabled/>
								</c:when>
								<c:otherwise>
									<input type="email" name="email" placeholder="이메일"
										maxlength="20" value="${user.email}" disabled/>
								</c:otherwise>
							</c:choose></td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit"
							class="btn full maincolor" value="${loginuser.uid eq 'admin' ? '삭제하기' : '탈퇴하기' }" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<%@ include file="../common/bottom.jsp"%>
</body>
</html>