package com.semi.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.semi.demo.entity.Board;
import com.semi.demo.entity.BookMark;
import com.semi.demo.entity.Review;
import com.semi.demo.entity.User;
import com.semi.demo.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService bsv;

	/** C : 리스트 출력 */
	@RequestMapping({ "/list/{view}", "/list" })
	public String list(@PathVariable(required = false) Integer view, HttpServletRequest req, Model model) {
		HttpSession ss = req.getSession();
		view = view == null ? 0 : view;

		// jsp --> 타이틀 전달
		String[] btitle = { "전체 리스트", "이달의 유치원", "나와 가까운 유치원" };
		model.addAttribute("btitle", btitle[view]);

		// top active용 --> 나중에 필터로 변경될 수 있음
		ss.setAttribute("menu", view.toString());
		User u = (User) ss.getAttribute("loginuser"); // 로그인 유저(세션) 받아오기
		List<Board> list = bsv.viewList(view, u);

		if (u != null) {
			List<BookMark> mlist = bsv.mGetList(u.getUid());
			model.addAttribute("mlist", mlist);
		}
		model.addAttribute("blist", list); // 결과에 맞는 리스트 jsp 전달
		return "board/list";
	}

	/** C : 메인 */
	@RequestMapping("/main")
	public String mainList(HttpServletRequest req, Model model) {
		HttpSession ss = req.getSession();
		ss.setAttribute("menu", "3");

		String tag = req.getParameter("findtag") == null ? "#유치원" : req.getParameter("findtag");
		String area = req.getParameter("findarea") == null ? "전체" : req.getParameter("findarea");
		area = !area.isBlank() ? area.replaceAll("[^ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9]", "").replaceAll("value", "") : "전체";

		List<Board> list = bsv.tagFind(tag, area);
		model.addAttribute("blist", list);

		ss.setAttribute("findarea", area);
		ss.setAttribute("findtag", bsv.tagSplit(tag));
		return "/board/main";
	}

	/** C : 게시물 디테일 */
	@RequestMapping("/detail/{bid}")
	public String detail(@PathVariable int bid, Model model, HttpServletRequest req) {
		HttpSession ss = req.getSession();
		User u = (User) ss.getAttribute("loginuser");
		Board b_ = bsv.bInfo(bid);
		
		/** 조회수 증가 */
		// 조회수 증가. 단, 본인이 읽거나 댓글 작성후에는 제외.
		if (u == null || !u.getUid().equals(b_.getUid())) {
			bsv.increaseViewCount(bid);
		}
		
		
		Board b = bsv.bInfo(bid);
		model.addAttribute("b", b);
		List<Review> rlist = bsv.rGetList(bid);
		model.addAttribute("rlist", rlist);
		
		return "board/detail";
	}

	/** C : 리뷰작성 */
	@PostMapping("/review")
	public String review(@ModelAttribute Review r, HttpServletRequest req, Model model) {
		if (r.getGrade() == 0) {
			model.addAttribute("msg", "별점을 등록해주세요!");
			model.addAttribute("url", "/board/detail/" + r.getBid());
			return "user/msg";
		}else if (r.getContent().isEmpty() || r.getContent() == null) {
			model.addAttribute("msg", r.getGrade()+"점 주신 이유를 함께 작성해주세요!");
			model.addAttribute("url", "/board/detail/" + r.getBid());
			return "user/msg";
		}
		
		HttpSession ss = req.getSession();
		User u = (User) ss.getAttribute("loginuser");
		bsv.rInsert(r, u);
		return "redirect:/board/detail/" + r.getBid();
	}

	/** C : 게시물 작성 */
	@GetMapping("/write")
	public String writeForm() {
		return "board/write";
	}

	@PostMapping("/write")
	public String write(MultipartHttpServletRequest req, @ModelAttribute Board b) {
		MultipartFile file = req.getFile("file");
		HttpSession ss = req.getSession();
		User u = (User) ss.getAttribute("loginuser");

		bsv.bInsert(b, file, u);
		return "redirect:/board/list";
	}

	/** 북마크 등록 */
	@RequestMapping("/bookmark/{bid}")
	public String like(@PathVariable int bid, Model model, HttpServletRequest req) {
		User u = (User) req.getAttribute("loginuser");
		bsv.mInsert(u.getUid(), bid);
		return "redirect:/board/list";
	}

}
