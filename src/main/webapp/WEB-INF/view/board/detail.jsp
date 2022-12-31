<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
pageContext.setAttribute("newline", "\n");
%>


<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../common/heading.jsp"%>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<link rel="stylesheet"
	href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="/review.css" />
</head>

<body>
	<%@ include file="../common/top.jsp"%>


	<div class="container">
		<div class="row" style="justify-content: space-evenly">
			<%-- <%@ include file="../common/aside.jsp"%> --%>

			<!-- Detail -->
			<div class="col-lg-8">
				<!-- Info -->
				<div class="detail-title">
					<!-- 이미지 -->
					<div class="detailbox">
						<img src="/thum/${b.thum }" id="descimg" />
					</div>
					<div class="space"></div>
					<!-- Info -->
					<div class="detail-content">
						<div class="title_bookmark pb-3">
							<div>
								<div class="board-view-tag">
									<span class="maincolor">${b.area }</span>
									<c:set var="t" value="${fn:split(b.tag,'/')}"></c:set>
									<c:forEach var="w" items="${t}">
										<span class="subcolor">${w}</span>
									</c:forEach>
								</div>
								<h3 style="padding-top: 1rem; padding-bottom: 0.1em">${b.title }
								</h3>
								<div>
									<p class="board-view-cnt">
										<span>조회 ${b.viewCnt }</span> <span>리뷰 ${b.reCnt }</span> <span>북마크
											${b.likeCnt }</span>
									</p>
								</div>
							</div>
							<div>
								<span class="material-symbols-outlined fill"
									style="font-size: 28px"
									onclick="location.href='/board/bookmark/${b.bid}/${loginuser.uid}'">
									bookmark </span>
							</div>
						</div>
						<div>
							<table class="board-view-infomation">
								<tr>
									<th>평점</th>
									<td>
										<div class="info-star">
											<c:forEach begin="1" end="${b.grade_ }">
												<span class="fa-star fa on"></span>
											</c:forEach>
											<c:forEach begin="1" end="${5 - b.grade_ }">
												<span class="fa-star fa"></span>
											</c:forEach>
											<span style="font-size: 16px; font-weight: 300;">&nbsp;${b.grade }</span>
										</div>
									</td>
								</tr>
								<tr>
									<th>주소</th>
									<td>${b.addr }</td>
								</tr>
								<tr>
									<th>연락처</th>
									<td>${b.tel }</td>
								</tr>
								<tr>
									<th>영업시간</th>
									<td>${b.closeTime }</td>
								</tr>
								<tr>
									<th>홈페이지</th>
									<td><a href='${b.homepage }'>${b.homepage }</a></td>
								</tr>
							</table>
						</div>
					</div>
				</div>
				<!-- Info -->
				<!-- content -->
				<div class="mt-2 mx-1">
					<div class="board-view-content py-5">${b.content }</div>
				</div>
				<!-- content -->
				<div class="border-bottom mb-5"></div>
				<!--리뷰-->
				<div class="board-review-place">
					<h3 class="pb-3"
						style="padding-top: 1rem; padding-bottom: 0.1em; text-align: center;">
						${b.title }을 '리뷰'해 주세요!</h3>
					<form class="form-inline" action="/board/review" method="post">
						<input type="hidden" name="grade" value="0" /> <input
							type="hidden" name="bid" value="${b.bid }" />
						<c:choose>
							<c:when test="${empty loginuser}">
								<div class="starrr" id="star2"></div>
								<div class="reply-write pt-4">
									<textarea class="board-input" id="content" name="content"
										rows="3" placeholder="로그인 후 리뷰 작성이 가능합니다!"
										style="height: 180px; font-size: 18px;" disabled></textarea>
								</div>
							</c:when>
							<c:otherwise>
								<div class="starrr" id="star1"></div>
								<div class="your-choice-was pt-1" style="display: none">
									<span class="choice"></span>점 주신 이유를 함께 적어주세요!😊
								</div>
								<div class="reply-write pt-4">
									<textarea class="board-input" id="content" name="content"
										rows="3" placeholder="리뷰 등록" style="height: 180px"></textarea>
									<button type="submit" class="btn maincolor">등록</button>
								</div>
							</c:otherwise>
						</c:choose>
					</form>
				</div>
				<!--리뷰-->

				<!-- 댓글 -->
				<div class="reply-content pt-3">
					<c:forEach var="r" items="${rlist }">
						<c:choose>
							<c:when test="${empty loginuser.uid or r.uid ne loginuser.uid }">
								<div class="d-flex flex-row mt-3">
									<div class="card subcolor w-75">
										<div class="card-body">
											<div class="le-padding">
												<div class="re-star mb-2">
													<c:forEach begin="1" end="${r.grade }">
														<span class="fa-star fa on"></span>
													</c:forEach>
													<c:forEach begin="1" end="${5 - r.grade }">
														<span class="fa-star fa"></span>
													</c:forEach>
												</div>
												<div class="line"></div>
												<div class="reply-decs">${r.content }</div>
												<div class="reply-info mt-2">
													<span>${r.uname }</span> <span>${fn:replace(r.modTime,'T',' ')}</span>
												</div>
											</div>
										</div>
									</div>
								</div>
							</c:when>
							<c:otherwise>
								<div class="d-flex flex-row-reverse mt-3">
									<div class="card subcolor w-75">
										<div class="card-body text-end">
											<div class="ri-padding">
												<div class="re-star mb-2">
													<c:forEach begin="1" end="${r.grade }">
														<span class="fa-star fa on"></span>
													</c:forEach>
													<c:forEach begin="1" end="${5 - r.grade }">
														<span class="fa-star fa"></span>
													</c:forEach>
												</div>
												<div class="line"></div>
												<div class="reply-decs">${r.content }</div>
												<div class="reply-info mt-2">
													<span>${fn:replace(r.modTime,'T',' ')}</span> <span>${r.uname }</span> 
												</div>
											</div>
										</div>
									</div>
								</div>
							</c:otherwise>
						</c:choose>
					</c:forEach>


				</div>
				<!-- 댓글 -->
			</div>
			<!--  Detail -->
		</div>
	</div>
	<%@ include file="../common/bottom.jsp"%>
	<script
		src="http://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.1/jquery.js"></script>
	<script src="/review.js"></script>
	<script>
		$('#star1').starrr({
			change : function(e, value) {
				if (value) {
					$('.your-choice-was').show();
					$('.choice').text(value);
					$('input[name=grade]').attr('value', value);
				} else {
					$('.your-choice-was').hide();
				}
			},
		});
		$('#star2').starrr({
			readOnly : true
		})
	</script>
	<script type="text/javascript">
		(function(i, s, o, g, r, a, m) {
			i['GoogleAnalyticsObject'] = r;
			(i[r] = i[r] || function() {
				(i[r].q = i[r].q || []).push(arguments);
			}), (i[r].l = 1 * new Date());
			(a = s.createElement(o)), (m = s.getElementsByTagName(o)[0]);
			a.async = 1;
			a.src = g;
			m.parentNode.insertBefore(a, m);
		})(window, document, 'script',
				'//www.google-analytics.com/analytics.js', 'ga');

		ga('create', 'UA-39205841-5', 'dobtco.github.io');
		ga('send', 'pageview');
	</script>
</body>
</html>
