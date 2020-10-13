package com.google.rough;

import com.google.base.Page;
import com.google.pages.HomePage;
import com.google.pages.LoginPage;
import com.google.pages.ZohoAppPage;
import com.google.pages.crm.accounts.AccountsPage;
import com.google.pages.crm.accounts.CreateAccountPage;

public class LoginTest {

	public static void main(String[] args) {

		HomePage home = new HomePage();
		LoginPage lp = home.goTOLogin();
		ZohoAppPage zp = lp.doLogin("harishch548@gmail.com", "loveisdamngood");
		zp.gotoCRM();
		AccountsPage account = Page.menu.gotoAccounts();
		CreateAccountPage cap = account.gotoCreateAccount();
		cap.createAccount("Harish");
	}

}
