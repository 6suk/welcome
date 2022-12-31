<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<nav class="navbar navbar-expand navbar-light fixed-top">
	<div class="container-md px-md-5">
		<ul class="navbar-nav">
			<a class="navbar-brand" href="/board/list"> <img
				src='/img/logo.png' class="logo" />
			</a>
			<li class="nav-item"><a
				class="nav-link ${menu eq '3' ? 'active' : ''}" href="/board/main">Home</a>
			</li>
			<li class="nav-item"><a
				class="nav-link ${menu eq '0' ? 'active' : ''}" href="/board/list/0">전체리스트</a>
			</li>
			<li class="nav-item"><a
				class="nav-link ${menu eq '1' ? 'active' : ''}" href="/board/list/1">이달의
					유치원</a></li>
			<c:choose>
				<c:when test="${!empty loginuser.uid}">
					<li class="nav-item"><a
						class="nav-link ${view eq '2' ? 'active' : ''}" href="/board/list/2">나와
							가까운 유치원</a></li>
				</c:when>
			</c:choose>
			<c:choose>
				<c:when test="${loginuser.uid eq 'admin'}">
					<li class="nav-item"><a
						class="nav-link ${menu eq 'user' ? 'active' : ''}"
						href="/user/list">사용자</a></li>
				</c:when>
			</c:choose>
		</ul>
		<c:choose>
			<c:when test="${empty loginuser.uid}">
				<ul class="navbar-nav">
					<li class="nav-item" style="font-weight: 800"><a
						class="nav-link ${menu eq 'login' ? 'active' : ''}"
						href="/user/login">로그인</a></li>
					<li class="nav-item"><a
						class="nav-link ${menu eq 'reg' ? 'active' : ''}"
						href="/user/register">회원가입</a></li>
				</ul>
			</c:when>
			<c:otherwise>
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" href="/user/logout">로그아웃</a>
					</li>
				</ul>
			</c:otherwise>
		</c:choose>



	</div>
</nav>