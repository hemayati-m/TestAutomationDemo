package rightel.ocs.report;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter { 
	
	public static ExtentReports generateExtentReport() {
				
		ExtentReports report = new ExtentReports();
		File reportFile = new File(System.getProperty("user.dir") + "\\src\\test\\java\\rightel\\ocs\\report\\extentreport.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportFile);
		
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("Sanity Report");
		sparkReporter.config().setDocumentTitle("Rightel Ocs Test Suite");
		sparkReporter.config().setTimeStampFormat("yyyy/mm/dd hh:mm:ss");
		
		report.attachReporter(sparkReporter);
		report.setSystemInfo("Application Url:", "http://10.201.123.3");
		report.setSystemInfo("Browser Name", "Chrome");
		report.setSystemInfo("Operating System", System.getProperty("os.name"));
		report.setSystemInfo("Username", System.getProperty("user.name"));
		report.setSystemInfo("Java Version", System.getProperty("java.version"));
		
		return report;
		
		
	}

}
