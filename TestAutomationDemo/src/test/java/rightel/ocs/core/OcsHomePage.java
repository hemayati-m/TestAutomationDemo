package rightel.ocs.core;

//import static rightel.ocs.core.OcsCoreClass.*;
import static rightel.ocs.core.OcsBasePage.isElementPresent;
import static rightel.ocs.properties.allObjects.home_sitemapBtn_xpath;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class OcsHomePage implements IPage {

	@FindBy(xpath = home_sitemapBtn_xpath)	private WebElement sitemapBtn;
	
	private WebDriver driver;
	private By byCloseTabBtn = By.xpath("//div[@role='tablist']//mat-icon");
	private WebDriverWait wait;
	
	public OcsHomePage(WebDriver driver) {
		
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));		
	}

	public SiteMapPage goto_SiteMap() {

		
		// if a tab was open, close that
		if(isElementPresent(byCloseTabBtn)) {
			
			driver.findElement(byCloseTabBtn).click();
		}
		
		//waiting for Sitemap link visible and clickable		
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//span[@jhitranslate='global.menu.site-map']")));		

		sitemapBtn.click();

		if (wait.until(ExpectedConditions.titleIs("Site Map"))) {

			return PageFactory.initElements(driver, SiteMapPage.class);
		}
		
		return null;
	}

}
