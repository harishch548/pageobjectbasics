package com.google.pages;

import org.openqa.selenium.By;

import com.google.base.Page;
import com.google.pages.crm.CRMHomePage;

public class ZohoAppPage extends Page {

	public CRMHomePage gotoCRM() {
		click("CRMlink");
		return new CRMHomePage();
	}

	public void gotoSalesIQ() {
		driver.findElement(By.xpath("(//div[@class='app-nm'])[text()='SalesIQ']")).click();
	}
}
