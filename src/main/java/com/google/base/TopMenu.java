package com.google.base;

import org.openqa.selenium.WebDriver;

import com.google.pages.crm.accounts.AccountsPage;

public class TopMenu{

	
	WebDriver driver;
	
	public TopMenu(WebDriver driver){
		
		this.driver = driver;
	}
	
	public void gotoHome() {

	}


	public void gotoLeads() {

	}

	public AccountsPage gotoAccounts() {
		
		
		Page.click("accountstab");
		return new AccountsPage();
	}

	public void gotoContacts() {

	}
	
	
	public void signOut(){
		
		
	}

}
