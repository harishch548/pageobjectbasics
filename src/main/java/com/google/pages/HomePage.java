package com.google.pages;

import org.openqa.selenium.By;

import com.google.base.Page;

public class HomePage extends Page {

	public void goTOSupport() {
		driver.findElement(By.xpath("//*[@class='zh-support']")).click();
	}

	public void goTOSignUp() {
		driver.findElement(By.xpath("//*[@class='zh-signup']")).click();
	}

	public LoginPage goTOLogin() {
		click("loginlink");
		return new LoginPage();
	}

	public void goTOLearnMore() {
		driver.findElement(By.xpath("//a[@class='learn-more'][@href='one/?ireft=ohome']")).click();
	}

	public void validateFooterLinks() {

	}

}
