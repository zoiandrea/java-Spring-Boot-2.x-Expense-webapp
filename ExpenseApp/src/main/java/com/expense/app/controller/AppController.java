package com.expense.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppController.class);

	@GetMapping("/")
	public String homePage() {
		LOGGER.info("===== Welcome To ExpenseApp ===");
		return "login.html";
	}

}
