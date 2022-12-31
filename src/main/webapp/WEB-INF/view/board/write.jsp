<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../common/heading.jsp"%>
<script src="https://cdn.ckeditor.com/4.18.0/standard/ckeditor.js"></script>
</head>

<body>
	<%@ include file="../common/top.jsp"%>


	<div class="container">
		<div class="row" style="justify-content: space-evenly">
			<%@ include file="../common/aside.jsp"%>

			<div class="inputtb content col-lg-8">
				<!-- 타이틀 -->
				<div class="inputtb content-title pb-4">
					<h3>게시글 쓰기</h3>
					<div class="">
						<button class="btn small subcolor"
							onclick="location.href='/board/list'">&lt;
							List</button>
					</div>
				</div>
				<!-- 타이틀 끝 -->
				<form action="/board/write" class="pt-4 mx-3" method="post"
					enctype="multipart/form-data">
					<table class="inputtb board-desc">
						<tr>
							<td><input style="border: 1px solid #d1d1d1;"
								class="board-input" type="text" placeholder="유치원명" name="title"
								maxlength="128" required /></td>
						</tr>
						<tr>
							<td><input style="border: 1px solid #d1d1d1;"
								class="board-input" type="text" placeholder="주소" name="addr"
								maxlength="128" required /></td>
						</tr>
						<tr>
							<td><select name='area' required>
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
									<option value="충청도">충청도</option>
									<option value="경상도">경상도</option>
									<option value="전라도">전라도</option>
									<option value="제주도">제주도</option>
							</select></td>
						</tr>
						<tr>
							<td><input style="border: 1px solid #d1d1d1;"
								class="board-input" type="text" placeholder="연락처" name="tel"
								maxlength="128" /></td>
						</tr>

						<tr>
							<td><input type="checkbox" name="tag" value="0">호텔 <input
								type="checkbox" name="tag" value="1">유치원 <input
								type="checkbox" name="tag" value="2">실내 <input
								type="checkbox" name="tag" value="3">야외 <input
								type="checkbox" name="tag" value="4">미용</td>
						</tr>
						<tr>
							<td><input style="border: 1px solid #d1d1d1;"
								class="board-input" type="text" placeholder="운영시간" name="closeTime"
								maxlength="128" /></td>
						</tr>
						<tr>
							<td><input style="border: 1px solid #d1d1d1;"
								class="board-input" type="text" placeholder="홈페이지" name="homepage"
								maxlength="128" /></td>
						</tr>
						
						<tr>
							<td><input type="checkbox" name="rec" value="1">관리자 추천</td>
						</tr>
						<tr>
							<td><textarea class="board-input" name="content"
									placeholder="내용" maxlength="5000" rows="10"></textarea></td>
						</tr>
						<tr>
							<td><input style="border: 1px solid #d1d1d1;"
								class="board-input" type="file" name="file" />썸네일 등록</td>
						</tr>
						<tr>
							<td colspan="2" class="multibtn pt-4"><input type="submit"
								class="btn full maincolor" value="글쓰기" />
								<div class="space10"></div> <input type="reset"
								class="btn full subcolor" value="취소" /></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
	<%@ include file="../common/bottom.jsp"%>
	<script>
		CKEDITOR.replace('content', {
			filebrowserImageUploadUrl : '/board/upload',
			filebrowserUploadMethod : 'form',
			height : 400
		});
	</script>
</body>
</html>
