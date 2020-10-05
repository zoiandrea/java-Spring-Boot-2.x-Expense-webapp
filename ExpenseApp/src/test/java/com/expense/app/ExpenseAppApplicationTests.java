package com.expense.app;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.expense.app.controller.AppController;

@SpringBootTest
class ExpenseAppApplicationTests {

	@Autowired
	private AppController appController;

	@Test
	void contextLoads() {
		//unit test to check if application context is loading correctly
		assertThat(appController).isNotNull();
	}

}
