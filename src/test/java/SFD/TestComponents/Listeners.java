package SFD.TestComponents;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


import SFD.resources.ExtentReportsNG;

public class Listeners extends BaseTest implements ITestListener {
	ExtentReports extentreports = ExtentReportsNG.getReportObject();
	ExtentTest extentTest;
	ThreadLocal<ExtentTest> threadLocal =new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest = extentreports.createTest(result.getMethod().getMethodName());
		threadLocal.set(extentTest); //unique thread iD //save the obejct like set	

	}

	@Override
	public void onTestSuccess(ITestResult result) {

		threadLocal.get().log(Status.PASS, "Test Passed");
		

	}

	@Override
	public void onTestFailure(ITestResult result) {

		
		threadLocal.get().fail(result.getThrowable());
		try {
			driver= (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String filepath =getScreenShot(result.getMethod().getMethodName(),driver);
		
		threadLocal.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		extentreports.flush();
		
		//Manual Opening
		
		String extentReportPath ="C:\\QAFOX\\HybridFrameworksTestng\\SeleniumFrameworksDesign\\test-output\\Extentreports\\extentReport.html";
		File extentfileReport = new File(extentReportPath);
		
		try {
			Desktop.getDesktop().browse(extentfileReport.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
