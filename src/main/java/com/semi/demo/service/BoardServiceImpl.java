package com.semi.demo.service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.semi.demo.dao.BoardDAO;
import com.semi.demo.dao.BookmarkDAO;
import com.semi.demo.dao.FindDAO;
import com.semi.demo.dao.ReviewDAO;
import com.semi.demo.entity.Board;
import com.semi.demo.entity.BookMark;
import com.semi.demo.entity.Review;
import com.semi.demo.entity.User;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO bdao;

	@Autowired
	private BookmarkDAO mdao;

	@Autowired
	private FindDAO fdao;

	@Autowired
	private ReviewDAO rdao;

	@Autowired
	ResourceLoader resourceLoader;

	@Override
	public List<Board> viewList(int view, User u) {
		List<Board> list = null;
		String uid = null;

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
			uid = u.getUid();
			list = bdao.areaList(u.getArea());
			return list;

		case B_LIKE:
			// 북마크 페이지
			uid = u.getUid();
			list = mdao.GetLikeList(uid);
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
		b.setUid(u.getUid());

		/** 태그 */
		if (b.getTag() != null) {
			b.setTag(b.getTag().replace(",", "/"));
		}

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
		mdao.likeUp(bid);
	}

	@Override
	public void mDelete(String uid, int bid) {
		mdao.mDelete(uid, bid);
		mdao.likeDown(bid);
	}

	@Override
	public List<Integer> bmGetList(User u) {
		if (u == null) {
			return null;
		} else {
			return mdao.mGetList(u.getUid());
		}
	}

	@Override
	public List<Board> tagFind(String tag, String area) {
		StringBuilder sb = new StringBuilder();
		boolean btag = tag.isBlank() ? false : true;
		boolean barea = area.isBlank() ? false : true;
		String[] tagBox_ = btag ? tag.trim().replaceAll("[^ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9]", "").split("value") : null;
		area = barea ? area.replaceAll("[^ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9]", "").replaceAll("value", "") : "전체";
		area = "전체".equals(area) ? "|" : area;
		List<Board> list = null;

		if (btag) {
			sb.append(".*");
			for (String a : tagBox_) {
				if (!a.isBlank() && !a.isEmpty()) {
					sb.append(a).append(".*");
				}
			}
			list = fdao.findATag(sb.toString(), area);
		}
		// 태그가 들어오지 않았을 때
		else {
			list = fdao.findArea(area);
		}
		list.forEach(x -> x.getBid());
		return list;
	}

	@Override
	public void rInsert(Review r, User u) {
		r.setUid(u.getUid());
		rdao.insert(r);

		// 리뷰 등록 후 평균 구하기 + boardDao 등록
		double avg = rdao.gradeAvg(r.getBid());
		System.out.println(avg);
		bdao.gradeAvgUpdate(avg, r.getBid());

		// 리뷰 등록 후 reCut + 1
		bdao.reCntUpdate(r.getBid());

	}

	@Override
	public List<Review> rGetList(int bid) {
		return rdao.getList(bid);
	}

	@Override
	public String tagSplit(String tag) {
		String[] tagBox_ = !tag.isBlank() ? tag.trim().replaceAll("[^ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9#]", "").split("value") : null;
		StringBuilder sb = new StringBuilder();
		if (!tag.isBlank()) {
			for (String a : tagBox_) {
				sb.append(a).append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}

	/** 조회수 증가 */
	@Override
	public void increaseViewCount(int bid) {
		bdao.increaseCount(bid);
	}

}
