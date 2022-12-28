package com.semi.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.semi.demo.entity.User;
import com.semi.demo.service.TestService;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private TestService sv;
	
	@RequestMapping("/list")
	public String list(Model model) {
		List<User> list = sv.getList();
		model.addAttribute("userList", list);
		return "test/list";
	}

}
