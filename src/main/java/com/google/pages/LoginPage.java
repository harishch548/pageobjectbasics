package com.google.pages;

import com.google.base.Page;

public class LoginPage extends Page {

	public ZohoAppPage doLogin(String username, String password) {
		type("email", username);
		click("nextbtn");
		type("password", password);
		click("signinbtn");
		return new ZohoAppPage();

	}

}
