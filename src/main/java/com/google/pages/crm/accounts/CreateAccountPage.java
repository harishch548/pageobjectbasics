package com.google.pages.crm.accounts;

import com.google.base.Page;

public class CreateAccountPage extends Page {

	public void createAccount(String accountName) {
		type("accountname",accountName);

	}

}
