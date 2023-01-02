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
							onclick="location.href='/board/list'">&lt; List</button>
					</div>
				</div>
				<!-- 타이틀 끝 -->
				<form action="/board/update" class="pt-4 mx-3" method="post"
					enctype="multipart/form-data">
					<input type="hidden" name="bid" value="${b.bid }">
					<table class="inputtb board-desc">
						<tr>
							<td><input style="border: 1px solid #d1d1d1;"
								class="board-input" type="file" name="file"/></td>
						</tr>
						<tr>
							<td><input style="border: 1px solid #d1d1d1;"
								class="board-input" type="text" placeholder="유치원명" name="title"
								maxlength="128" value="${b.title}" required /></td>
						</tr>
						<tr>
							<td><input style="border: 1px solid #d1d1d1;"
								class="board-input" type="text" placeholder="주소" name="addr"
								maxlength="128" value="${b.addr}" required /></td>
						</tr>
						<tr>
							<td><select name='area' class="form-select board-input"
								required>
								<option value="서울" ${b.area eq '서울' ? 'selected' : '' }>서울</option>
								<option value="부산" ${b.area eq '부산' ? 'selected' : '' }>부산</option>
								<option value="대구" ${b.area eq '대구' ? 'selected' : '' }>대구</option>
								<option value="인천" ${b.area eq '인천' ? 'selected' : '' }>인천</option>
								<option value="광주" ${b.area eq '광주' ? 'selected' : '' }>광주</option>
								<option value="대전" ${b.area eq '대전' ? 'selected' : '' }>대전</option>
								<option value="울산" ${b.area eq '울산' ? 'selected' : '' }>울산</option>
								<option value="세종" ${b.area eq '세종' ? 'selected' : '' }>세종</option>
								<option value="경기" ${b.area eq '경기' ? 'selected' : '' }>경기</option>
								<option value="강원" ${b.area eq '강원' ? 'selected' : '' }>강원</option>
								<option value="충청" ${b.area eq '충청' ? 'selected' : '' }>충청</option>
								<option value="경상" ${b.area eq '경상' ? 'selected' : '' }>경상</option>
								<option value="전라" ${b.area eq '전라' ? 'selected' : '' }>전라</option>
								<option value="제주" ${b.area eq '제주' ? 'selected' : '' }>제주</option>
							</select></td>
						</tr>
						<tr>
							<td><input style="border: 1px solid #d1d1d1;"
								class="board-input" type="text" placeholder="연락처" name="tel"
								maxlength="128" value="${b.tel}"/></td>
						</tr>
						<tr>
							<td>
									<input type="checkbox" name="tag" value="호텔" class="form-check-input"><label class="form-check-label">호텔</label>
									<input type="checkbox" name="tag" value="유치원" class="form-check-input"><label class="form-check-label">유치원</label>
									<input type="checkbox" name="tag" value="실내" class="form-check-input"><label class="form-check-label">실내</label>
									<input type="checkbox" name="tag" value="야외" class="form-check-input"><label class="form-check-label">야외</label>
									<input type="checkbox" name="tag" value="미용" class="form-check-input"><label class="form-check-label">미용</label>
							</td>
						</tr>
						<tr>
							<td><input style="border: 1px solid #d1d1d1;"
								class="board-input" type="text" placeholder="운영시간"
								name="closeTime" maxlength="128" value="${b.closeTime }"/></td>
						</tr>
						<tr>
							<td><input style="border: 1px solid #d1d1d1;"
								class="board-input" type="text" placeholder="홈페이지"
								name="homepage" maxlength="128" value="${b.homepage }"/></td>
						</tr>

						<tr>
							<td>
							<div class="form-check">
							<input type="checkbox" name="rec" value="1" class="form-check-input"><label class="form-check-label">관리자 추천</label></div>
							</td>
						</tr>
						<tr>
							<td><textarea class="board-input" name="content"
									placeholder="내용" maxlength="5000" rows="10" >${b.content }</textarea></td>
						</tr>
						<tr>
							<td colspan="2" class="multibtn pt-4"><input type="submit"
								class="btn full maincolor" value="수정하기" />
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
