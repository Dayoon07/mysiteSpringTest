package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	@RequestMapping(value = "/main", method = { RequestMethod.GET, RequestMethod.POST })
	public String main() {
		System.out.println("MainController.main()");
		return "/main/index";
	}
	
	@RequestMapping(value = "/gallery", method = { RequestMethod.GET })
	public String gallery() {
		System.out.println("MainController.gallery()");
		return "/gallery/list";
	}

}
