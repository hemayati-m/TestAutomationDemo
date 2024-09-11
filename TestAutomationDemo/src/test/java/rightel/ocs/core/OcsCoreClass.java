package rightel.ocs.core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringDecorator;

import rightel.ocs.listener.DriverListener;
import rightel.ocs.util.ExcelReader;

public class OcsCoreClass { 

	/*
	 * 
	 * Initializing properties, xls, creating db connection, generating logs, init
	 * Webdriver
	 * 
	 */

	public static Properties config = new Properties();
	public static  WebDriver originDriver = null;	
	public static  WebDriver driver = null;
	public static File xlsFile = null;
	public static File csvFile = null;
	public static final int IMPLICIT_WAIT_TIME = 10;
	public static final int PAGE_LOAD_TIME = 10;


	
	public static OcsLoginPage init() {

		DriverListener listener = new DriverListener();
		EventFiringDecorator<WebDriver> decorator = new EventFiringDecorator<WebDriver>(listener);	

		
		if (originDriver == null) {

			try {				
				
				// Loading configuration property file
				FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
						+ "\\src\\test\\java\\rightel\\ocs\\properties\\config.properties");
				config.load(fis);


				// loading Web driver
				if (config.getProperty("browser").equals("firefox")) {
					
					// Creating an object of the FirefoxOptions Class
					FirefoxOptions firefoxOptions = new FirefoxOptions();

					// Using the setAcceptInsecureCerts() method to pass parameter as False
					firefoxOptions.setAcceptInsecureCerts(false);

					// add headless argument to driver
					if (config.getProperty("headless").equals("true")) {

						firefoxOptions.addArguments("-headless");
					}

					originDriver = new FirefoxDriver(firefoxOptions);
					
					//Assign decorator to WebDriver
					driver = decorator.decorate(originDriver);

				} else if (config.getProperty("browser").equals("chrome")) {

					// Create instance of ChromeOptions Class
					ChromeOptions chromeOprions = new ChromeOptions();

					// Using the accept insecure certificate method with true as parameter to accept
					// the untrusted certificate
					chromeOprions.setAcceptInsecureCerts(true);

					// add headless argument to driver
					if (config.getProperty("headless").equals("true")) {

						chromeOprions.addArguments("-headless");

					}
					
					originDriver = new ChromeDriver(chromeOprions);
					
					//Assign decorator to WebDriver
					driver = decorator.decorate(originDriver);
				}

				// Maximize browser
				driver.manage().window().maximize();

				// Implicit wait
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT_TIME));

				// Set PageLoad time
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(PAGE_LOAD_TIME));

				driver.get(config.getProperty("testsite"));

			} catch (Exception e) {

				System.out.println("Connection time out... \n" + e.toString());
				System.exit(1);

			}
		}

		return PageFactory.initElements(driver, OcsLoginPage.class);
	}


	public static void quitDriver() {

		driver.quit();
	}
}
