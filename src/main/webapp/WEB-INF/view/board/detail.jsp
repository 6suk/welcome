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
</head>

<body>
	<%@ include file="../common/top.jsp"%>


	<div class="container">
		<div class="row" style="justify-content: space-evenly">
			<%@ include file="../common/aside.jsp"%>

				<div class="inputtb content col-lg-8">
					<div class="inputtb board-view pb-4">
						<div>
							<div class="board-view-bid">
								<span>${board.bid }</span>
							</div>
							<h2 class="board-view-title">${board.btitle }</h2>
							<div class="board-view-info">
								<span>${board.uname }</span><span>${fn:replace(board.modtime,'T','
              ')}</span>
							</div>
						</div>

						<div class="content multibtn">
							<button class="btn action maincolor"
								onclick="location.href='<%= BLIST %>?page=#'">
								목록</button>
							<div class="space3"></div>
							<button class="btn action subcolor" disabled >
								수정</button>
							<div class="space3"></div>
							<button class="btn action subcolor" disabled>
								삭제</button>
						</div>
					</div>

					<div class="row mt-2 mx-1">
						<div class="board-view-desc py-3">
							<div>
								<p class="board-view-file">
									<span>첨부파일</span>
										<a href="/bbs/board/download?file=#" download="#">#</a>
								</p>
							</div>
							<div>
								<p class="board-view-cnt">
									<span>조회 ${board.viewCnt }</span><span>댓글 ${board.reCnt }</span>
								</p>
							</div>
						</div>

						<div class="board-view-content py-5">
							${board.content }</div>

						</div>
					</div>
				</div>
		</div>
	<%@ include file="../common/bottom.jsp"%>
</body>
</html>
