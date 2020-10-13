package com.google.testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.google.pages.HomePage;
import com.google.pages.LoginPage;
import com.google.utilities.Utilities;

public class LoginTest extends BaseTest {

	@Test(dataProviderClass = Utilities.class, dataProvider = "dp")
	public void loginTest(Hashtable<String, String> data) {

		HomePage home = new HomePage();
		LoginPage lp = home.goTOLogin();
		lp.doLogin(data.get("username"), data.get("password"));

	}

}
