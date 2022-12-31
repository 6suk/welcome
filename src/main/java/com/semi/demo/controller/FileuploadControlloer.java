package com.semi.demo.controller;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping("/board")
public class FileuploadControlloer {

	@ResponseBody
	@PostMapping("/upload")
	public String upload(MultipartHttpServletRequest req) {
		String callback = req.getParameter("CKEditorFuncNum");
		String error = "";
		String url = "";
		Map<String, MultipartFile> map = req.getFileMap();
		for (Map.Entry<String, MultipartFile> pair : map.entrySet()) {
			MultipartFile file = pair.getValue();
			String fname = file.getOriginalFilename();
			String now = LocalDateTime.now().toString().substring(0, 22).replaceAll("[-T:.]", "");
			int idx = fname.lastIndexOf('.');
			fname = now + fname.substring(idx); // 유니크한 파일 이름으로 변경
			File fileName = new File(fname);
			try {
				file.transferTo(fileName);
			} catch (Exception e) {
				e.printStackTrace();
			}
			url = "/thum/" + fileName;
		}
		String data = "<script> " + "     window.parent.CKEDITOR.tools.callFunction(" + callback + ", '" + url + "', '"
				+ error + "'); " + "</script>";
		return data;
	}

}