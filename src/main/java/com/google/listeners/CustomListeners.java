package com.google.listeners;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import com.google.base.Page;
import com.google.utilities.MonitoringMail;
import com.google.utilities.TestConfig;
import com.google.utilities.Utilities;
import com.relevantcodes.extentreports.LogStatus;

public class CustomListeners extends Page implements ITestListener, ISuiteListener {

	public static String messageBody;

	public void onTestFailure(ITestResult result) {
		String Methodname = result.getName().toString().trim();

		try {
			Utilities.CaptureScreenshot(Methodname);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ReportNG
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		Reporter.log("Click to get Screenshot");
		Reporter.log("<a target = \"_blank\" href=" + Utilities.screenshotpath + ">Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<br>");
		Reporter.log("<a target = \"_blank\" href=" + Utilities.screenshotpath + "><img src=" + Utilities.screenshotpath
				+ " height=200 width=200></img></a>");

		// Extent_Reports
		test.log(LogStatus.FAIL, result.getName().toUpperCase() + " Failed with exception :" + result.getThrowable());
		test.log(LogStatus.FAIL, test.addScreenCapture(Utilities.screenshotpath));
		rep.endTest(test);
		rep.flush();
	}

	public void onTestSuccess(ITestResult result) {

		test.log(LogStatus.PASS, result.getName().toUpperCase() + " PASS");
		rep.endTest(test);
		rep.flush();

	}

	public void onTestStart(ITestResult result) {
		test = rep.startTest(result.getName().toUpperCase());
		
		  if (!Utilities.isTestRunnable(result.getName(), excel)) { throw new
		  SkipException("Skipping the test " + result.getName().toUpperCase() +
		  "as the Runmode is NO"); }
		 

	}

	public void onTestSkipped(ITestResult result) {

		test.log(LogStatus.SKIP, "Skipping the test " + result.getName().toUpperCase() + " as the Runmode is NO");
		rep.endTest(test);
		rep.flush();

	}

	public void onFinish(ISuite suite) {

		MonitoringMail mail = new MonitoringMail();

		try {
			messageBody = "http://" + InetAddress.getLocalHost().getHostAddress()
					+ ":8080/job/PageObjectModelLiveProj/ExtentReports/";
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messageBody);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}