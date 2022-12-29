package com.semi.demo.controller;

import java.util.List;
import java.util.Map;

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
		ss.setAttribute("view", view);
		User u = (User) ss.getAttribute("loginuser");	// 로그인 유저(세션) 받아오기
		List<Board> list = bsv.viewList(view, u);
		
		if (u != null) {
			List<BookMark> mlist = bsv.mGetList(u.getUid());
			model.addAttribute("mlist", mlist);
		}
		model.addAttribute("blist", list);	// 결과에 맞는 리스트 jsp 전달
		return "board/list";
	}

	/** C : 게시물 디테일 */
	@RequestMapping("/detail/{bid}")
	public String detail(@PathVariable int bid, Model model) {
		Board b = bsv.bInfo(bid);
		model.addAttribute("board", b);
		return "board/detail";
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
	@RequestMapping("/bookmark/{bid}/{uid}")
	public String detail(@PathVariable int bid, @PathVariable String uid, Model model) {
		bsv.mInsert(uid, bid);
		return "redirect:/board/list";
	}

}
