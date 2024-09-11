package rightel.ocs.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static rightel.ocs.properties.allObjects.*;


public class BalanceInquiryPage extends OcsBasePage {

	@FindBy(id = BalanceInquery_SubsNumber_id)
	private WebElement subsNumberBox;
	@FindBy(id = BalanceInquery_AccountCode_id)
	private WebElement accountCodeBox;
	@FindBy(id = BalanceInquery_CustomerCode_id)
	private WebElement customerCodeBox;
	@FindBy(xpath = BalanceInquery_SearchBtn_xpath)
	private WebElement searchBtn;
	@FindBy(xpath = BalanceInquery_ClearBtn_xpath)
	private WebElement clearBtn;
	@FindBy(xpath = BalanceInquery_InquiryBtn_xpath)
	private WebElement inquiryBtn;
	@FindBy(xpath = BalanceInquery_InquiryGrid_xpath)
	private WebElement inquiryGrid;
	
	private WebDriver driver;
	

	public BalanceInquiryPage(WebDriver driver) {

		this.driver = driver;
	}

	public boolean clickOn_Inquiry() {

		inquiryBtn.click();
		if (isElementPresent(By.xpath("//div[@fxlayout='column']"))) {
			return true;
		}

		return false;
	}

	public boolean search(String subsNumber, String accountCode, String custCode) {

		if (!subsNumber.equals("")) {

			subsNumberBox.clear();
			subsNumberBox.sendKeys(subsNumber);
		}

		if (!accountCode.equals("")) {

			accountCodeBox.clear();
			accountCodeBox.sendKeys(accountCode);
		}

		if (!custCode.equals("")) {

			customerCodeBox.clear();
			customerCodeBox.sendKeys(custCode);
		}

		clickOn_SearchBtn();
		return true;
	}

	private void clickOn_SearchBtn() {

		searchBtn.click();

	}

}
