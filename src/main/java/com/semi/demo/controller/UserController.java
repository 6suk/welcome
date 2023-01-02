package com.semi.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.semi.demo.entity.User;
import com.semi.demo.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;

	/** C : 사용자 리스트 출력 ★ */
	@RequestMapping("/list")
	public String list(HttpServletRequest req, Model model) {
		HttpSession ss = req.getSession();
		ss.setAttribute("menu", "user");
		List<User> list = service.getList();
		model.addAttribute("userList", list);
		return "user/list";
	}

	/** C : 회원가입 */
	@GetMapping("/register")
	public String registerFrom(HttpServletRequest req) {
		HttpSession ss = req.getSession();
		ss.setAttribute("menu", "reg");
		return "user/register";
	}

	@PostMapping("/register")
	public String register(HttpServletRequest req, Model model) {
		String uid = req.getParameter("uid");
		String pwdBox[] = req.getParameterValues("pwd");
		String uname = req.getParameter("name");
		String email = req.getParameter("email");
		String area = req.getParameter("area");

		User u = service.get(uid);
		// uid가 db에 있으면
		if (u != null) {
			model.addAttribute("msg", "중복 아이디입니다.");
			model.addAttribute("url", "/user/register");
			return "user/msg";
		}
		// 없으면
		else {
			if (!pwdBox[0].equals(pwdBox[1])) {
				model.addAttribute("msg", "비밀번호 오류");
				model.addAttribute("url", "/user/register/");
				return "/user/msg";
			} else {
				User u1 = new User(uid, pwdBox[0], uname, email, area);
				service.register(u1);
				model.addAttribute("msg", "회원가입이 완료되었습니다.");
				model.addAttribute("url", "/user/login");
				return "/user/msg";
			}
		}
	}

	/** 회원 정보 수정 */
	@GetMapping("/update/{uid}")
	public String updateForm(@PathVariable String uid, Model model) {
		User user = service.get(uid);
		model.addAttribute("user", user);
		return "user/update";
	}

	@PostMapping("/update")
	public String update(HttpServletRequest req, Model model) {
		HttpSession ss = req.getSession();
		User loginuser = (User) ss.getAttribute("loginuser");

		String uid = req.getParameter("uid");
		String pwdbox[] = req.getParameterValues("pwd");
		String uname = req.getParameter("name");
		String email = req.getParameter("email");
		String area = req.getParameter("area");
		User u = new User();

		if (email.isEmpty()) {
			u.setUid(uid);
			u.setPwd(pwdbox[0]);
			u.setUname(uname);
			u.setArea(area);
		} else {
			u.setUid(uid);
			u.setPwd(pwdbox[0]);
			u.setUname(uname);
			u.setArea(area);
			u.setEmail(email);
		}

		if (!pwdbox[0].equals(pwdbox[1])) {
			model.addAttribute("msg", "비밀번호 오류");
			model.addAttribute("url", "/user/update/" + uid);
			return "/user/msg";
		} else {
			service.update(u);
			if (!loginuser.getUid().equals("admin")) {
				/** 세션 등록 추가 */
				u = service.get(uid);
				ss.setAttribute("loginuser", u);
			}
			model.addAttribute("msg", "개인정보 수정 완료");
			model.addAttribute("url", "/board/list");
			return "/user/msg";
		}

	}

	/** 로그인 */
	@GetMapping("/login")
	public String loginForm(HttpServletRequest req) {
		HttpSession ss = req.getSession();
		ss.setAttribute("menu", "login");
		return "user/login";
	}

	@PostMapping("/login")
	public String login(HttpServletRequest req, Model model) {
		String uid = req.getParameter("uid");
		String pwd = req.getParameter("pwd");
		int result = service.login(uid, pwd);
		HttpSession ss = req.getSession();

		switch (result) {
		case UserService.CORRECT_LOGIN:
			User u = service.get(uid);
			/** 세션등록 User 객체로 변경 */
			ss.setAttribute("loginuser", u);
			return "redirect:/board/main";

		case UserService.WRONG_PASSWORD:
			model.addAttribute("msg", "잘못된 패스워드 입니다.");
			model.addAttribute("url", "/user/login");
			return "/user/msg";

		case UserService.UID_NOT_EXIST:
			model.addAttribute("msg", "아이디를 확인해주세요.");
			model.addAttribute("url", "/user/login");
			return "/user/msg";

		default:
			return "";
		}
	}

	/** 로그아웃 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest req, Model model) {
		HttpSession ss = req.getSession();
		ss.invalidate();
		model.addAttribute("msg", "로그아웃 되었습니다.");
		model.addAttribute("url", "/user/login");
		return "/user/msg";
	}

	/** 회원 삭제 */
	@GetMapping("/delete/{uid}")
	public String deleteForm(@PathVariable String uid, Model model) {
		User u = service.get(uid);
		model.addAttribute("user", u);
		return "user/delete";
	}

	@PostMapping("/delete")
	public String delete(HttpServletRequest req, Model model) {
		String uid = req.getParameter("uid");
		service.delete(uid);
		
		model.addAttribute("msg", "탈퇴 완료 되었습니다.");
		model.addAttribute("url", "/board/main");
		return "/user/msg";
	}

}
