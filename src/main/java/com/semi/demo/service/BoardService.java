package com.semi.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.semi.demo.entity.Board;
import com.semi.demo.entity.BookMark;
import com.semi.demo.entity.User;

public interface BoardService {
	public static final String TAG[] = { "호텔", "유치원", "실내", "야외", "미용" };
	public static final int B_ALL = 0;
	public static final int B_REC = 1;
	public static final int B_AREA = 2;
	
	/** 조건에 맞는 리스트 출력 */
	List<Board> viewList(int view, User u);

	/** 디테일 페이지 출력 */
	Board bInfo(int bid);

	/** 게시물 등록 */
	void bInsert(Board b, MultipartFile thum);

	/** 게시물 수정 */
	void bUpdate(Board b);

	/** 게시물 삭제 */
	void bDelete(int bid);
	
	/** 북마크 등록 */
	void mInsert(String uid, int bid);
	
	/** 북마크 삭제 */
	void mDelete(String uid, int bid);
	
	/** 북마크 리스트 */
	List<BookMark> mGetList(String uid);
	

}
