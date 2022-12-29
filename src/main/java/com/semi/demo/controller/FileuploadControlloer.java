package com.semi.demo.controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.semi.demo.entity.Board;
import com.semi.demo.entity.FileEntity;
import com.semi.demo.service.BoardService;

@Component
@RequestMapping("/board")
public class FileuploadControlloer {
	
	@Autowired
	private BoardService bsv;

	@GetMapping("/upload")
	public String uploadForm() {
		return "board/write";
	}

	@PostMapping("/upload")
	public String upload(MultipartHttpServletRequest req, @ModelAttribute Board b) {
		MultipartFile file = req.getFile("file");

		String now = LocalDateTime.now().toString().substring(0,22).replaceAll("[-T:.]", "");
		int idx = file.getOriginalFilename().lastIndexOf(".");
		String newFileName = now + file.getOriginalFilename().substring(idx);
		
		try {
			file.transferTo(new File(newFileName));
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(b.toString());
		System.out.println(newFileName);
		return null;
	}

	@Value("${spring.servlet.multipart.location}")
	String uploadDir;

	@GetMapping("/download")
	public ResponseEntity<Resource> download(@ModelAttribute FileEntity fe) {
		Path path = Paths.get(uploadDir + "/" + fe.getFileName());
		
		try {
			String contentType = Files.probeContentType(path);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentDisposition(ContentDisposition.builder("attachment")
					.filename(fe.getFileName(), StandardCharsets.UTF_8).build());
			headers.add(HttpHeaders.CONTENT_TYPE, contentType);
			Resource resource = new InputStreamResource(Files.newInputStream(path));
			return new ResponseEntity<>(resource, headers, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}