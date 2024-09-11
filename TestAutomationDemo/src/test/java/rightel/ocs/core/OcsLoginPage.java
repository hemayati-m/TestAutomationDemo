package rightel.ocs.core;

import static rightel.ocs.core.OcsBasePage.*;
import static rightel.ocs.properties.allObjects.*;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class OcsLoginPage implements IPage {
	
	@FindBy(xpath = login_username_xpath) 	private WebElement usernameBox;	
	@FindBy(xpath = login_password_xpath)	private WebElement passwordBox;	
	@FindBy(xpath = login_loginBtn_xpath)	private WebElement loginBtn;
	
	By byHomePageToolBox = By.xpath("//mat-toolbar");
	By byErrorText = By.xpath("//*[@class='text-danger']");
	
	private WebDriver driver;
	private String errorText;	
	private WebDriverWait wait;
	
	public OcsLoginPage(WebDriver driver) {
		
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}
	
	public IPage doLogin(String username, String password) {
		
		try {
			//Checking login form loaded
			if (checkFormLoaded()) {
				
				//input username and password
				usernameBox.sendKeys(username);
				passwordBox.sendKeys(password);
				loginBtn.submit();
				
				//waiting for home page load
				wait.until(ExpectedConditions.visibilityOfElementLocated(byHomePageToolBox));
				
				//return home page object
				return PageFactory.initElements(driver, OcsHomePage.class);			
			}	
			
			//if home page don't load, calling setError method to find reason of errors.
		} catch(TimeoutException t) {
			
			setErrorMessage();
			
		} catch(Exception e) {
			
			setErrorMessage();			
		}	
		return this;
	}
	
	//this method check login page successfully loaded or not
	private boolean checkFormLoaded() {
		
		if(wait.until(ExpectedConditions.titleIs("Welcome Ocs"))) {
			
			return true;
		}
		return false;
	}
	
	private void setErrorMessage() {
		
		if (isElementPresent(byErrorText)) {
			
			errorText = driver.findElement(byErrorText).getText();			
		}
	}
	
	public String getErrorMessage() {
		
		return errorText;
	}
}
