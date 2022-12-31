<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../common/heading.jsp"%>
</head>

<body>
	<%@ include file="../common/top.jsp"%>
	<!-- =========================== Main Start =========================== -->
	<div class="container">
		<div class="row" style="justify-content: space-evenly">
			<%@ include file="../common/aside.jsp"%>


			<!-- // content - 리스트  -->
			<div class="content col-lg-8">
				<div class="content-title pb-3">
					<h3>사용자 리스트</h3>
				</div>
				<table class="content-desc">
					<tr>
						<th>아이디</th>
						<th>이름</th>
						<th>email</th>
						<th>주소</th>
						<th>가입일</th>
						<th>수정 / 삭제</th>
					</tr>

					<c:forEach var="u" items="${userList}" varStatus="loop">
						<tr>
							<td>${u.uid}</td>
							<td>${u.uname}</td>
							<td>${u.email}</td>
							<td>${u.area}</td>
							<td>${fn:substringBefore(loginuser.regDate, 'T')}</td>
							<td>
								<button class="btn action subcolor" type="button"
									onclick="location.href='/user/update/${u.uid}'">수정</button>
								<button class="btn action subcolor" type="button"
									onclick="location.href='/user/delete/${u.uid}'">삭제</button>
							</td>
						</tr>
					</c:forEach>
				</table>

			</div>
			<!-- content - 리스트 // -->
		</div>
	</div>
	<!-- =========================== Main End =========================== -->
	<%@ include file="../common/bottom.jsp"%>
</body>
</html>