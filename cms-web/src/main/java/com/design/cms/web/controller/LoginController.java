package com.design.cms.web.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {
	
	private static Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping("/index")
	public String index(HttpSession session){
		log.info("login***index...");
		return "login";
	}
	
}
