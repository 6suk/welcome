<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>정보 수정</title>

</head>
<body>
	<div class="contain-01">
		<div class="contain-02">
			<div class="title">
				  <h1>정보 수정</h1>
          <input
            type="button"
            value="Home"
            onclick="location.href ='#''"
          />
        </div>

        <form action="/user/update" method="post">
            <input type="hidden" name="uid" value="${user.uid}" />
          <table>
            <tr>
              <th>가입일</th>
              <td>
                <input
                  type="text"
                  name="regDate"
                  required
                  disabled
                  value="${user.regDate}"
                />
              </td>
            </tr>
            <tr>
              <th>아이디</th>
              <td>
                <input
                  type="text"
                  name="uid"
                  placeholder="아이디"
                  required
                  disabled
                  value="${user.uid}"
                />
              </td>
            </tr>
            <tr>
              <th>패스워드</th>
              <td>
                <input
                  type="password"
                  name="pwd"
                  placeholder="*패스워드"
                  maxlength="60"
                />
              </td>
            </tr>
            <tr>
              <th>패스워드 확인</th>
              <td>
                <input
                  type="password"
                  name="pwd"
                  placeholder="*패스워드 확인"
                  maxlength="60"
                />
              </td>
            </tr>
            <tr>
              <th>이름</th>
              <td>
                <input
                  type="text"
                  name="name"
                  placeholder="*이름"
                  maxlength="10"
                  required
                  value="${user.uname}"
                />
              </td>
            </tr>
            <tr>
              <th>이메일</th>
              <td>
              	<c:choose>
	              	<c:when test="${empty user.email}">
	                <input type="email" name="email" placeholder="이메일" maxlength="20" value="${null}"/>              		
	              	</c:when>
	              	<c:otherwise>
	                <input type="email" name="email" placeholder="이메일" maxlength="20" value="${user.email}" />                	
	              	</c:otherwise>
              	</c:choose>
              </td>
            </tr>
            	<tr>
						<th>주소</th>
						<td><select name="area">
							<option value="서울">서울</option>
							<option value="부산">부산</option>
							<option value="대구">대구</option>
							<option value="인천">인천</option>
							<option value="광주">광주</option>
							<option value="대전">대전</option>
							<option value="울산">울산</option>
							<option value="세종">세종</option>
							<option value="경기">경기</option>
							<option value="강원">강원</option>
							<option value="충청">충청</option>
							<option value="경상">경상</option>
							<option value="전라">전라</option>
							<option value="제주">제주</option>
							</select>
						</td>
					</tr>
					<tr>
            <tr>
              <td colspan="2">
                <input type="submit" value="정보수정" />
              </td>
            </tr>
          </table>
        </form>
      </div>
    </div>
  </body>
</html>