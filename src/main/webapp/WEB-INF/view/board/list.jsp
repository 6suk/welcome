<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
					<h3>${btitle }</h3>
					<div class="">
						<button class="btn mdi maincolor"
							onclick="location.href='/board/write'">글쓰기</button>
					</div>
				</div>
				<div class="content-list">
					<c:forEach var="b" items="${blist }">
						<div class="content-list-col">
							<div class="descbox">
								<img src="/thum/${b.thum }" id="descimg" />
							</div>
							<div class="content-list-bookmark">
								<h5>${b.title }</h5>
										<span class="material-symbols-outlined"
											onclick="location.href='/board/bookmark/${b.bid}/${uid}'">
											bookmark </span>
										<c:forEach var="m" items="${mlist }">
											<c:choose>
												<c:when test="${m.bid eq b.bid }">
													<span class="material-symbols-outlined fill"
														onclick="location.href='/board/bookmarkdel/${b.bid}/${uid}'">
														bookmark </span>
												</c:when>
												<c:otherwise>
													<span class="material-symbols-outlined"
														onclick="location.href='/board/bookmark/${b.bid}/${uid}'">
														bookmark </span>
												</c:otherwise>
											</c:choose>
										</c:forEach>
							</div>
							<p>${b.addr }</p>
							<p>
								<c:set var="t" value="${fn:split(b.tag,'/')}"></c:set>
								<c:forEach var="w" items="${t}">
									<span class="badge subcolor">${w}</span>
								</c:forEach>
							</p>
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
