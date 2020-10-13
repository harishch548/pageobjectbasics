package com.google.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.google.utilities.ExcelReader;
import com.google.utilities.ExtentManager;
import com.google.utilities.Utilities;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Page {
	public static WebDriver driver;
	public static Properties Config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\com\\google\\excel\\testdata.xlsx");
	public static WebDriverWait wait;
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
	public static String browser;
	public static TopMenu menu;

	public Page() {
		if (driver == null) {

			try {
				fis = new FileInputStream(System.getProperty("user.dir")
						+ "\\src\\test\\resources\\com\\google\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Config.load(fis);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			log.debug("Config File loaded");
			try {
				fis = new FileInputStream(System.getProperty("user.dir")
						+ "\\src\\test\\resources\\com\\google\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			log.debug("OR File loaded");

			// Jenkins Browser Filter Configuration
			if (System.getenv("browser") != null && !System.getenv("browser").isEmpty()) {

				browser = System.getenv("browser");
			} else {
				browser = Config.getProperty("browser");
			}
			Config.setProperty("browser", browser);

			if (Config.getProperty("browser").equals("firefox")) {

				WebDriverManager.firefoxdriver().setup();
				FirefoxOptions options = new FirefoxOptions();
				options.addPreference("dom.webnotifications.enabled", false);
				driver = new FirefoxDriver(options);

			} else if (Config.getProperty("browser").equals("chrome")) {

				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				log.debug("Chrome Launched !!!");
			} else if (Config.getProperty("browser").equals("ie")) {

				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
			}

			driver.manage().window().maximize();
			driver.get(Config.getProperty("testsiteurl"));
			log.debug("Navigated to " + Config.getProperty("testsiteurl"));
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 5);
			menu = new TopMenu(driver);
		}

	}

	public static void quit() {

		driver.quit();

	}

	// ***Common_Keywords***
	public boolean isElementPresent(By by) {

		try {
			driver.findElement(by);
			return true;

		} catch (NoSuchElementException e) {

			return false;

		}

	}

	public static void verifyEquals(String expected, String actual) throws IOException {

		try {

			Assert.assertEquals(actual, expected);
		} catch (Throwable t) {

			Utilities.CaptureScreenshot(null);

			// ReportNG
			System.setProperty("org.uncommons.reportng.escape-output", "false");
			Reporter.log("<br>" + " Verification Failure :" + t.getMessage() + "<br>");
			Reporter.log("<a target = \"_blank\" href=" + Utilities.screenshotpath + "><img src="
					+ Utilities.screenshotpath + " height=200 width=200></img></a>");
			Reporter.log("<br>");
			Reporter.log("<br>");

			// ExtentReports
			test.log(LogStatus.FAIL, " Failed with exception :" + t.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(Utilities.screenshotpath));
		}
	}

	public static void click(String locator) {

		driver.findElement(By.xpath(OR.getProperty(locator))).click();
		log.debug("clicking on an Element : "+locator);
		test.log(LogStatus.INFO, " Clicking on :" + locator);

	}

	public static void type(String locator, String value) {

		driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		log.debug("Typing in an Element : "+locator+" entered value as "+value);
		test.log(LogStatus.INFO, " typing in :" + locator, " entered vaue as :" + value);
	}

	static WebElement dropdown;

	public static void select(String locator, String value) {

		dropdown = driver.findElement(By.xpath(OR.getProperty(locator)));
		Select select = new Select(dropdown);
		select.selectByVisibleText(value);
		log.debug("Selecting from an element : "+locator+" value asa : "+value);
		test.log(LogStatus.INFO, " Selecting from dropdown :" + locator, " value as :" + value);

	}

}
