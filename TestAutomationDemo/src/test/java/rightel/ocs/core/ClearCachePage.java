package rightel.ocs.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static rightel.ocs.properties.allObjects.*;

public class ClearCachePage extends OcsBasePage {

	@FindBy(xpath = ClearCache_ClearDataDic_xpath)
	private WebElement ClearDataDicBtn;
	@FindBy(xpath = ClearCache_ClearBalanceTypes_xpath)
	private WebElement ClearBalanceTypesBtn;
	@FindBy(xpath = ClearCache_ClearRce_xpath)
	private WebElement ClearRceBtn;
	
	
	private WebDriver driver;
	

	public ClearCachePage(WebDriver driver) {

		this.driver = driver;
	}
	

	public void clearDatadicCache() {

		ClearDataDicBtn.click();
		setResponseMessage();
	}

	public void clearBalanceTypesCache() {

		ClearBalanceTypesBtn.click();
		setResponseMessage();
	}

	public void clearRceCache() {

		ClearRceBtn.click();
		setResponseMessage();
	}

}
