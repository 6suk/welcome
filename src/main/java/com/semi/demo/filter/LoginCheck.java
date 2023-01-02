package com.semi.demo.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import com.semi.demo.entity.User;

@Component
public class LoginCheck extends HttpFilter implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession ss = req.getSession();

		String uri = req.getRequestURI();

		List<String> url = Arrays.asList("/board/review", "/board/bookmark", "/user/update", "/user/delete");
		for (String a : url) {
			if (uri.contains(a)) {
				User u = (User) ss.getAttribute("loginuser");
				if (u == null) {
					req.setAttribute("msg", "로그인 후 이용 가능합니다.");
					req.setAttribute("url", "/user/login");
					req.getRequestDispatcher("/WEB-INF/view/user/msg.jsp").forward(request, response);
					return;
				}
				break;
			}
		}

		List<String> adurl = Arrays.asList("/board/write", "/user/list");
		for (String a : adurl) {
			if (uri.contains(a)) {
				User u = (User) ss.getAttribute("loginuser");
				if (u == null || !u.getUid().equals("admin")) {
					req.setAttribute("msg", "관리자만 접근 가능한 페이지입니다.");
					req.setAttribute("url", "/board/main");
					req.getRequestDispatcher("/WEB-INF/view/user/msg.jsp").forward(request, response);
					return;
				}
				break;
			}
		}

		chain.doFilter(request, response);
	}

}
