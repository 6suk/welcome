<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../common/heading.jsp"%>
<link rel="stylesheet"
	href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="/review.css" />
</head>

<body>
	<%@ include file="../common/top.jsp"%>
	<!-- =========================== Main Start =========================== -->
	<div class="container">
		<div class="row" style="justify-content: space-evenly">
			<%@ include file="../common/aside.jsp"%>


			<!-- // content - 리스트  -->
			<div class="content col-lg-8">
				<div class="content-title pb-2">
					<h3>${btitle }</h3>
					<c:choose>
						<c:when test="${loginuser.uid eq 'admin'}">
							<div class="">
								<button class="btn mdi maincolor"
									onclick="location.href='/board/write'">글쓰기</button>
							</div>
						</c:when>
					</c:choose>
				</div>
				<div class="content-list">
					<c:forEach var="b" items="${blist }">
						<div class="content-list-col">
							<div class="descbox">
								<c:choose>
									<c:when test="${b.reCnt eq 0 }">
										<div class="re-star descbox-star">
											<span class="" style="color: #444;">평점 미등록</span>
										</div>
									</c:when>
									<c:otherwise>
										<div class="re-star descbox-star">
											<span class="" style="color: #666;">평점</span>
											<c:forEach begin="1" end="${b.grade_ }">
												<span class="fa-star fa on"></span>
											</c:forEach>
											<c:forEach begin="1" end="${5 - b.grade_ }">
												<span class="fa-star fa"></span>
											</c:forEach>
										</div>
									</c:otherwise>
								</c:choose>

								<img src="/thum/${b.thum }" id="descimg"
									onclick="location.href='/board/detail/${b.bid}'" />
							</div>
							<div class="content-list-bookmark mb-2">
								<!-- 태그 -->
								<p class="board-view-tag">
									<c:set var="t" value="${fn:split(b.tag,'/')}"></c:set>
									<c:forEach var="w" items="${t}">
										<span class="subcolor">${w}</span>
									</c:forEach>
								</p>
								
								<!-- 북마크 -->
								<c:set var="fill" value=""/>
								<c:set var="url" value="bookmark/${b.bid }"/>
								<c:forEach var="m" items="${mlist }">
									<c:if test="${m eq b.bid }">
										<c:set var="fill" value="fill"/>
										<c:set var="url" value="bookmark/del/${b.bid }"/>
									</c:if>
								</c:forEach>
								
								<span class="bookmark material-symbols-outlined ${fill }"
									onclick="location.href='/board/${url}'">
									bookmark </span>
								<!-- 북마크 -->


							</div>
							<div class="main-list-title mb-3"
								onclick="location.href='/board/detail/${b.bid}'">
								<h5 class="mb-1">${b.title }</h5>
								<p>${b.addr }</p>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
			<!-- content - 리스트 // -->
		</div>
	</div>
	<!-- =========================== Main End =========================== -->
	<%@ include file="../common/bottom.jsp"%>
</body>
</html>
