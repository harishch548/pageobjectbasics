package com.google.pages.crm.accounts;

import com.google.base.Page;

public class AccountsPage extends Page {

	public CreateAccountPage gotoCreateAccount() {
		click("createaccountbtn");
		return new CreateAccountPage();

	}

	public void gotoImportAccounts() {

	}

}
