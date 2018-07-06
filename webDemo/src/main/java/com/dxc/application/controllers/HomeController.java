package com.dxc.application.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import th.co.toyota.sc2.client.CSC22110Constant;
import th.co.toyota.sc2.client.model.simple.CSC22110UserInfo;

@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@GetMapping("/home")
	public String home(HttpServletRequest request) {
		logger.debug("555555555555");
		CSC22110UserInfo simpleUser = (CSC22110UserInfo) request.getSession().getAttribute(CSC22110Constant.SESSION_USER_INFO);
		logger.info("simpleUser.getCountry() >>>>> " + simpleUser.getCountry());
		logger.info("simpleUser.getCompanyType() >>>> " + simpleUser.getCompanyType());
		logger.info("simpleUser.getCompany() >>>> " + simpleUser.getCompany());
		logger.info("simpleUser.getLocation() >>>> " + simpleUser.getLocation());
		logger.info("simpleUser.getUserId() >>>> " + simpleUser.getUserId());
		return "home";
	}
	
	@GetMapping("/home/js/home.js")
	public String js() {
		logger.debug("666666666666");
		return "home";
	}
}
