package com.javaex.controller;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;

@Controller
@RequestMapping(value = "/gallery")
public class GalleryController {
	
	@Autowired
	private GalleryService service;
	
	@RequestMapping(method = { RequestMethod.GET })
	public String gallery() {
		System.out.println("MainController.gallery()");
		return "/gallery/list";
	}
	
	@RequestMapping(value = "/imgUploadForm", method = { RequestMethod.GET })
	public String galleryUploadForm() {
		return "/gallery/uploadForm";
	}
	
	@RequestMapping(value = "/imgUpload", method = { RequestMethod.POST })
	public String imgUploadLogic(@RequestParam String writer, @RequestParam MultipartFile filename) {
	    String nowTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
	    String name = nowTime + filename.getOriginalFilename();
	    
	    String dir = "C:/springimg/";
	    
	    File uploadFile = new File(dir + name);
	    try {
	        filename.transferTo(uploadFile);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "error";
	    }

	    service.imgUpload(name);
	    return "redirect:/gallery/list";
	}
	
}
