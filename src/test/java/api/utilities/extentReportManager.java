package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class extentReportManager implements ITestListener {

	public ExtentSparkReporter esp;
	public ExtentReports er;
	public ExtentTest test;
	String repName;

	public void onStart(ITestContext context) {

		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName = "Test-Report-" + timestamp + ".html";
		esp = new ExtentSparkReporter(".\\reports\\" + repName);// path

		esp.config().setDocumentTitle("RestAssuredAutomationProject");// title of report
		esp.config().setReportName("PET store user API"); // name of report
		esp.config().setTheme(Theme.DARK);

		er = new ExtentReports();
		er.attachReporter(esp);
		er.setSystemInfo("Application", "PET store user api");
		er.setSystemInfo("operating system", System.getProperty("os.name"));
		er.setSystemInfo("User name", System.getProperty("user.name"));
		er.setSystemInfo("Environment", "QA");
		er.setSystemInfo("User", "Shravan");

	}

	public void onTestStart(ITestResult result) {
		test = er.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.PASS, "Test passed");
	}

	public void onTestFailure(ITestResult result) {
		test = er.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.FAIL, "Test failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());
	}

	public void onTestSkipped(ITestResult result) {
		test = er.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.SKIP, "Test skipped");
		test.log(Status.SKIP, result.getThrowable().getMessage());
	}

	public void onFinish(ITestContext context) {
		er.flush();
	}

}
