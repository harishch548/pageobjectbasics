package com.google.testcases;

import org.testng.annotations.AfterSuite;

import com.google.base.Page;

public class BaseTest {

	@AfterSuite
	public void tearDown() {
		Page.quit();
	}

}
