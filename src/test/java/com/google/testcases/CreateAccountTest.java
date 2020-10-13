package com.google.testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.google.base.Page;
import com.google.pages.ZohoAppPage;
import com.google.pages.crm.accounts.AccountsPage;
import com.google.pages.crm.accounts.CreateAccountPage;
import com.google.utilities.Utilities;

public class CreateAccountTest {

	@Test(dataProviderClass = Utilities.class, dataProvider = "dp")
	public void createAccountTest(Hashtable<String, String> data) {

		ZohoAppPage zp = new ZohoAppPage();
		zp.gotoCRM();
		AccountsPage account = Page.menu.gotoAccounts();
		CreateAccountPage cap = account.gotoCreateAccount();
		cap.createAccount(data.get("accountname"));

	}
}
