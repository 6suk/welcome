<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isErrorPage="true"%>

<!DOCTYPE html>
<html>
<head>
    <%@ include file="../common/heading.jsp" %>
</head>

<body>
   	<%@ include file="../common/top.jsp"%>
    <div class="container" style="margin-top: 300px;">
        <div class="row">
            <div class="col-4"></div>
			<div class="col-4" style="text-align: center">
				<h4 style="color: #666" class="pb-1">404 Error</h4>
				<h1 class="txtmain pb-4">Internal Server Error</h1>
				<p>죄송합니다. 서비스 실행 중 오류가 발생했습니다.</p>
				<p>잠시 후 다시 시도해보세요.</p>
				<button class="btn mdi maincolor mt-4"
					onclick="location.href='/board/main'">Home</button>
			</div>
            <div class="col-4"></div>
        </div>
    </div>

    <%@ include file="../common/bottom.jsp" %>
</body>