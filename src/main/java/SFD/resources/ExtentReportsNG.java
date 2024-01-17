package SFD.resources;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportsNG {

	public static ExtentReports getReportObject() {
		
		
		File ExtentFilereportpath =new File(System.getProperty("user.dir")+"\\test-output\\Extentreports\\extentReport.html");
		
		ExtentReports extentReports =new ExtentReports();
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(ExtentFilereportpath);
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("SFD WEB Automation RESULTS");
		sparkReporter.config().setDocumentTitle("TEST RESULTS");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		
		extentReports.attachReporter(sparkReporter);
		extentReports.setSystemInfo("Tester", "SaranrajKumar");
		
		

		Properties configProp = new Properties();                      
		File configPropFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\SFD\\resources\\GobalData.properties");
		
		try {
			FileInputStream fisConfigProp = new FileInputStream(configPropFile);
			configProp.load(fisConfigProp);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		extentReports.setSystemInfo("Application URL",configProp.getProperty("URL"));
		extentReports.setSystemInfo("Browser Name",configProp.getProperty("browser"));
		extentReports.setSystemInfo("Email",configProp.getProperty("ValidEmail"));
		extentReports.setSystemInfo("Password",configProp.getProperty("ValidPassword"));
		extentReports.setSystemInfo("Operating System",System.getProperty("os.name"));
		extentReports.setSystemInfo("Username",System.getProperty("user.name"));
		extentReports.setSystemInfo("Java Version",System.getProperty("java.version"));
		
		return extentReports;
		

	}

}
