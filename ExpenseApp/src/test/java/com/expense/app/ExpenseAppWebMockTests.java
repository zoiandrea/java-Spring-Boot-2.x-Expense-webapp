package com.expense.app;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.expense.app.controller.AppController;

@WebMvcTest(AppController.class)
public class ExpenseAppWebMockTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private AppController appController;

	//@Test
	public void expenseAppHomePageTest() throws Exception {
		//unit test to check main controller is returning home page correctly
		when(appController.homePage()).thenReturn("login.html");
		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString("login.html")));
	}

}
