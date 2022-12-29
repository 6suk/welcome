package com.semi.demo.service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.semi.demo.dao.BoardDAO;
import com.semi.demo.dao.BookmarkDAO;
import com.semi.demo.dao.UserDAO;
import com.semi.demo.entity.Board;
import com.semi.demo.entity.BookMark;
import com.semi.demo.entity.User;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO bdao;
	
	@Autowired
	private UserDAO udao;

	@Autowired
	private BookmarkDAO mdao;

	@Autowired
	ResourceLoader resourceLoader;


	
	@Override
	public List<Board> viewList(int view, User u) {
		List<Board> list = null;
		
		String uid = null;
		if(u != null) {
			uid = u.getUid();
		}
		
		switch (view) {
		case B_ALL:
			// 전체 리스트
			list = bdao.bGetList();
			return list;
			
		case B_REC:
			// 관리자 추천 리스트
			list = bdao.recList();
			return list;
			
		case B_AREA:
			// 가까운 유치원 리스트
			list = bdao.areaList(u.getArea());
			return list;
		default:
			break;
		}
		return null;
		
	}

	@Override
	public Board bInfo(int bid) {
		return bdao.bInfo(bid);
	}
	
	@Value("${spring.servlet.multipart.location}")
	String uploadDir;

	@Override
	public void bInsert(Board b, MultipartFile thum, User u) {
		System.out.println(u.toString());
		b.setUid(u.getUid());
		
		/** 태그 */
		String[] tagBox = b.getTag().split(",");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < tagBox.length; i++) {
			int index = Integer.parseInt(tagBox[i]);
			if (i == 0)
				sb.append(TAG[index]);
			else
				sb.append("/").append(TAG[index]);
		}
		b.setTag(sb.toString());

		/** 유니크한 확장자명 */
		String now = LocalDateTime.now().toString().substring(0, 22).replaceAll("[-T:.]", "");
		int idx = thum.getOriginalFilename().lastIndexOf(".");
		String newFileName = now + thum.getOriginalFilename().substring(idx);

		try {
			// 썸네일 저장
			thum.transferTo(new File(newFileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		b.setThum(newFileName);
		bdao.bInsert(b);

		System.out.println("[게시물 업로드 완료!]");
	}

	@Override
	public void bUpdate(Board b) {
		bdao.bUpdate(b);
	}

	@Override
	public void bDelete(int bid) {
		bdao.bDelete(bid);
	}

	@Override
	public void mInsert(String uid, int bid) {
		mdao.mInsert(uid, bid);
		System.out.println("[북마크 등록 완료]");
	}

	@Override
	public void mDelete(String uid, int bid) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<BookMark> mGetList(String uid) {
		return mdao.mGetList(uid);
	}


}
